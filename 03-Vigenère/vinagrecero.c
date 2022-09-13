/*
 * Copyright (c) 2019 Gerardo Rubén López Hernández
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
#include<stdio.h>

int Vinagre(int ,int );
int TextoANumero(char);
char NumeroATexto(int);
int LongitudClave(int);


int TextoANumero(char C)
     {
       int Entero;                       
                  if((C>='a')&&(C<='z'))
                  Entero = C-97;
                  if((C>='A')&&(C<='Z'))
                  Entero = C-65;
      return Entero;
    }
    
int NumeroAtexto(int E)
    {
      char Caracter;
      if((E>= 0)&&(E<26))
      Caracter = 65+ E;
      return Caracter;
    }   

int Vinagre(int A,int B){
  int C;

  C=(A+B)%26;
  return C;
}




int main()
{
    char c,d,Cifrado;
    char Clave[5];
    char MinimoComunMultiplo[15];
    int i,j,Min, ContadorDeCiclo;

 
    for(i=0;i<=4;i++)
    Clave[i]=0;
   
    for(Min=0;Min<15;Min++)
    MinimoComunMultiplo[Min]=0;
      
    printf("Dame tu clave \n");
    
      
    
    
    for(j=0;j<5;j++)
                   {
                      c=getchar();
                      if(c != '\n')
                                  {
                                   Clave[j]=TextoANumero(c);
                                  }
                    }
    for(j=0;j<5;j++)
    MinimoComunMultiplo[j]=Clave[j];               
                    
    for(j=5;j<=9;j++)
    MinimoComunMultiplo[j]=Clave[j-5];
    
    for(j=10;j<15;j++)
        MinimoComunMultiplo[j]=Clave[j-10];
                   // inicializa el contador del ciclo a cero
    ContadorDeCiclo=0;      
    Cifrado=0;
        
    printf("Para terminar de ingresar un parrafo presiona la tecla entrada y terminar de ingresar todo el texto presiona cero \n ");
    
    while((d=getchar() ) != 48)
    {
           if( ((d>= 97)&&(d<=122)) && (ContadorDeCiclo<=15))
           {
               Cifrado=Vinagre(TextoANumero(d) , MinimoComunMultiplo[ContadorDeCiclo]);
              // printf("El cifrado de  %c es %d \n",d,Cifrado); 
               printf("%c ",NumeroAtexto(Cifrado));
               ContadorDeCiclo++;
               // printf("Contador de Ciclo igual a %d \n ",ContadorDeCiclo);
           }
           if( ((d>= 65)&&(d<=90)) && (ContadorDeCiclo<=15))
           {
               Cifrado=Vinagre(TextoANumero(d) , MinimoComunMultiplo[ContadorDeCiclo]);
               printf(" %c ",NumeroAtexto(Cifrado));
               //printf("El cifrado de  %c es %d \n",d,Cifrado);
               ContadorDeCiclo++;
               //printf("Contador de Ciclo igual a %d \n ",ContadorDeCiclo);
           }
             
           
            if(ContadorDeCiclo==15)
            { 
                ContadorDeCiclo=0;
             printf("    ");   
            }
            
            if(ContadorDeCiclo==3)
            printf("    ");
            
            if(ContadorDeCiclo==6)
            printf("    ");
            
            if(ContadorDeCiclo==9)
            printf("    ");
            
            if(ContadorDeCiclo==12)
            printf("    ");
            
            if(ContadorDeCiclo==15)
            printf("    ");

            
    }
           
  return 0;                                  
  
  
  
}
