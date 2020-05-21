using System;
using System.Numerics;
using System.Text;

namespace RSA
{
    class Program
    {
        static void Main(string[] args)
        {

            /////////////////////////////////
            //// 1. Generación de llaves ////
            /////////////////////////////////

            // 1. Dos números primos aleatorios.
            
            //string p = "37975227936943673922808872755445627854565536638199";
            //string q = "40094690950920881030683735292761468389214899724061";
            // string p = "61";
            // string q = "53";

            BigInteger p = 97;
            BigInteger q = 73;
            Console.WriteLine("p: " + p);
            Console.WriteLine("q: " + q);

            // 2. Se calcula n=p*q
            // BigInteger n = BigInteger.Parse("9516311845790656153499716760847001433441357");
            BigInteger n = BigInteger.Multiply(p, q);
            Console.WriteLine("n: " + n);

            // 3. Se calcula la función de Euler
            // Phi(n) = (p-1)(q-1)
            BigInteger phi = BigInteger.Multiply((p - 1), (q - 1));
            Console.WriteLine("phi: " + phi);

            // 4. Entero positivo 'e' menor que phi y que sea coprimo con phi   
            BigInteger e = 1139;
            Console.WriteLine("e: " + e);

            // 5. Se determina 'd' que satisfaga la congruencia e*d=1(mod Phi(n))
            // BigInteger d = BigInteger.Parse("5617843187844953170308463622230283376298685");
            // BigInteger d = BigInteger.ModPow(e, phi(phi - 1), phi);
            BigInteger d = 443;
            Console.WriteLine("d: " + d + "\n");


            ////////////////////
            //// 2. Encrypt ////
            ////////////////////
            const string plainText = "C";
            byte[] plainTextBytes = ASCIIEncoding.ASCII.GetBytes(plainText);
            BigInteger plainTextBigInteger = new BigInteger(plainTextBytes);
            //BigInteger plainTextBigInteger = 1985;
            /*if (plainTextBigInteger > n)
                throw new Exception();*/
            Console.WriteLine(plainText + " en vector de bits: " + plainTextBigInteger + "\n");

            // c = m^e (mod n)
            BigInteger C = BigInteger.ModPow(plainTextBigInteger, e, n);
            Console.WriteLine("Criptograma:  " + C);

            /////////////////////////
            //// 2. Desencriptar ////
            /////////////////////////
            // m = c^d (mod n)
            BigInteger D = BigInteger.ModPow(C, d, n);
            Console.WriteLine("Recuperando mensaje:  " + D);
     
            string decoded = ASCIIEncoding.ASCII.GetString(D.ToByteArray());
            Console.WriteLine("Texto plano: " + decoded);

        }
    }
}
