#include <stdio.h>
#include <stdlib.h>

/*
 * Lenstra
 * Manuel Diaz Diaz
 */

/*
 * usamos long long en vez de long para que quepan las multiplicaciones grandes en caso de ser requeridas.
 */
typedef unsigned long long _long;


/*
 * numero a factorizar es NUM
 */
_long NUM = 1073561597;

/*
 * El coeficiente a y B de la curva y^2 = x^3 + ax + B
 */
_long a ;
_long B ;


/* Esta es la estructura que define un punto con coordenadas x,y, la usaremos para manipular sumas de puntos
 * tambien aprovechamos para guardar la lambda que resulta de sumar dos puntos cuyo resultado es (x,y)
 * asi como el numerador y denomidador (num,den)
 */
typedef struct punto
{
  _long x, y;
  _long l;
  _long num, den;
} punto_t;

/*
 * Esta funcion calcula la combinacion lineal de x,y que da como resultado el mcd(x,y) 
 * o sea:
 *
 * x*r[0] + y*r[1] = mcd(x,y)
 *
 * recordemos que r[1] nos sirve como el inverso multiplicativo modulo x si x,y son primos relativos
 * esta funcion es usada por la funcion inverso_modular();
 */
void euclides_extendido (_long x, _long y, _long * rv)
{
  _long t, respaldoy = y;
  rv[0] = x;
  rv[1] = 1;
  rv[2] = 0;

  _long c, d;
  c = 0;
  d = 1;

  _long q;
  _long t1, t2;

  while (y != 0)
    {
      q = rv[0] / y;
      t1 = y;
      y = rv[0] - y * q;
      rv[0] = t1;

      t1 = c;
      t2 = d;

      c = rv[1] - c * q;
      d = rv[2] - d * q;
      rv[1] = t1;
      rv[2] = t2;

      if (rv[1] < 0)
	{
	  t = (respaldoy + rv[1]) % respaldoy;
	  rv[1] = t;
	}
    }
}

/* 
 * Calcula el inverso multiplicativo modulo NUM usando el algoritmo de euclides
 * esto es usado cuando en la curva eliptica calculamos lambda = r/s = r*inverso_modular(s) 
 */
_long inverso_modular (_long x, _long y)
{
  _long r[2];
  euclides_extendido (x, y, (_long *)&r);
  return r[1];
}

/*
 * Calcula el maximo comun divisor de x,y
 */
_long mcd (_long x, _long y)
{
  _long g;
  if (x < 0)
    x = -x;
  if (y < 0)
    y = -y;
  if (x + y == 0)
    exit (-1);
  g = y;
  while (x > 0)
    {
      g = x;
      x = y % x;
      y = g;
    }
  return g;
}

/*
 * Esta funcion reduce modulo NUM, sirve en caso de que x sea negativo para hacerlo positivo modulo n
 * ya que x%y en C si x es negativo regresa el mismo x y eso no nos sirve, queremos la clase
 * de equivalencia cuyo representante sea positivo
 */
_long modn (_long x)
{
  return (x + NUM) % NUM;
}

/*
 * Suma puntos de la curva eliptica y^2 = x^3 + ax + B con la operacion intuitiva
 * en el caso que p != q , tiene 3 subcasos 
 * a) si p=0
 * b) si q=0
 * c) si p = -q
 *
 */
punto_t suma_puntos_diferentes (punto_t p, punto_t q)
{
  punto_t r;

  if ((p.x == 0) & (p.y == 0))
    {
      return q;
    }

  if ((q.x == 0) & (q.y == 0))
    {
      return p;
    }

  if ((p.x == q.x) & (q.y == (-1 * p.y)))
    {
      r.x = 0;
      r.y = 0;
      return r;
    }


  r.l = modn ((q.y - p.y) * inverso_modular (q.x - p.x, NUM));
  r.num = modn (q.y - p.y);
  r.den = modn (q.x - p.x);
  r.x = modn (modn (r.l * r.l) + modn ((-1 * p.x)) + modn ((-1 * q.x)));
  r.y = modn ((r.l * (p.x - r.x)) - p.y);
  return r;
}

/*
 * Esta funcion calcula R = P+P = 2P 
 * en la curva y^2 = x^3 + ax + B
 */
punto_t suma_puntos_iguales (punto_t p)
{
  punto_t r;
  r.l = modn (((3 * (p.x * p.x)) + a) * inverso_modular (p.y * 2, NUM));
  r.num = modn ((3 * (p.x * p.x) + a));
  r.den = modn (p.y * 2);
  r.x = modn (modn ((r.l * r.l)) - modn (2 * p.x));
  r.y = modn ((r.l * (p.x - r.x)) - p.y);
  return r;
}

/*
 * Esta funcion calcula P+Q = R en y^2 + x^3 + ax + B
 * usando las dos funciones anteriores , (para solo usar una sola funcion que calcule sumas de puntos)
 *
 * Esta funcion deduce si P = Q entonces hay que usar la primer funcion , o si P != Q la segunda funcion
 *
 */
punto_t suma_puntos_en_general (punto_t p, punto_t q)
{
  if (son_iguales (p, q))
    {
      return suma_puntos_iguales (p);
    }
  return suma_puntos_diferentes (p, q);
}

/*
 * Esta funcion regresa 1 (verdadero) si P = Q y 0 si son distintos
 * con el fin de usarse como 
 *
 * if(son_iguales(p,q)) {
 * ...
 * }
 *
 */
int son_iguales (punto_t p, punto_t q)
{
  if ((p.x == q.x) & (p.y == q.y))
    {
      return 1;
    }
  return 0;
}

/*
 * Esta funcion calcula R  = P+P+...+P  (k veces) o sea R = kP
 * en y^2 = x^3 + ax + B
 * usando iteraciones de las funciones anteriores (hay maneras mas eficientes cuando la k es muy grande
 * pero en este caso no es necesario)
 */
punto_t mult_escalar (int k, punto_t p)
{
  punto_t r, t;
  r.x = 0;
  r.y = 0;
  int i;
  for (i = 0; i < k; i++)
    {
      t = suma_puntos_en_general (p, r);
      r = t;
    }
  return r;
}

/* Funcion principal */
int main (int argc, char **argv)
{

  punto_t inicial;		/*  (1,1) Es el punto de y^2 = x^3 + ax + B */
  punto_t t;
  int i, j, k;
  inicial.x = 1;
  inicial.y = 1;
  _long mcd1;

  /* Hay que dar el parametro en argv[1] el maximo k para iterar R = kP y en argv[2] la a de y^2 = x^3 + ax - a (usaremos siempre el (1,1) ) */
  if (argv[1] == NULL)
    {
      printf
	("Necesitas dar como parametro la cota k de la cual haremos k*(1,1) y la a de y^2 = x^3 + ax -a\n");
      exit (1);
    }
  if(argv[2] == NULL) {
	  printf("Necesitas darme como parametro la a de y^2 = x^3 + ax - a \n");
	  exit(1);
  }

  k = atoi (argv[1]);
  a = atoi(argv[2]);
  B = a*(-1);

/* Iteramos desde 2 hasta k */

  for (i = 2; i <= k; i++)
    {
      /* Sumamos i veces (1,1) */
      t = mult_escalar (i, inicial);

      printf
	("t = %i*(1,1)= (%lli,%lli), y^2 = x^3 + %lli x - %lli mod %lli\n", i,
	 t.x, t.y, a, a, NUM);

      /* Calculamos el maximo comun divisor del denominador de la pendiente con NUM */
      mcd1 = mcd (t.den, NUM);
      printf ("Con %i*(1,1) tenemos mcd(denominador=%lli,n=%lli) = %lli\n", i,
	      t.den, NUM, mcd1);
      printf ("-----------------\n");
      /* Si son primos relativos imprimimos */
      if (mcd1 != 1)
	{
	  printf ("A huevo %lli es factor de %lli usando a=%lli y en iteracion %d!!!\n", mcd1, NUM,a,i);
	  exit (1);
	}

    }
  /* Si termina la iteracion hay que cambiar de a en y^2 = x^3 + ax + B o intentar otra cota mas grande */
  printf
    ("NO ENCONTRE NINGUN FACTOR DE %lli con a=%lli y k=%d  intenta con una \"k\" mas grande u otro valor de a con y^2 = x^2 + ax - a\n",
     NUM,a,k);

  return 0;
}
