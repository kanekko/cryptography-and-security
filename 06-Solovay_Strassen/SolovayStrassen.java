import java.util.Scanner;
import java.util.Random;

public class SolovayStrassen {

    /**
     * Function to calculate jacobi (a/b)
     * 
     * @param  a [description]
     * @param  b [description]
     * @return   [description]
     */
    public long Jacobi(long a, long b) {
        if (b <= 0 || b % 2 == 0)
            return 0;
        long j = 1L;
        if (a < 0) {
            a = -a;
            if (b % 4 == 3)
                j = -j;
        }
        while (a != 0) {
            while (a % 2 == 0) {
                a /= 2;
                if (b % 8 == 3 || b % 8 == 5)
                    j = -j;
            }

            long temp = a;
            a = b;
            b = temp;

            if (a % 4 == 3 && b % 4 == 3)
                j = -j;
            a %= b;
        }
        if (b == 1)
            return j;
        return 0;
    }

    /**
     * Function to check if prime or not
     * 
     * @param  n         [description]
     * @param  iteration [description]
     * @return           [description]
     */
    public boolean isPrime(long n, int iteration) {
        /** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;

        Random rand = new Random();
        for (int i = 0; i < iteration; i++) {
            long r = Math.abs(rand.nextLong());
            long a = r % (n - 1) + 1;
            long jacobian = (n + Jacobi(a, n)) % n;
            long mod = modPow(a, (n - 1) / 2, n);
            if (jacobian == 0 || mod != jacobian)
                return false;
        }
        return true;
    }

    /**
     * Function to calculate (a ^ b) % c
     * 
     * @param  a [description]
     * @param  b [description]
     * @param  c [description]
     * @return   [description]
     */
    public long modPow(long a, long b, long c) {
        long res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= c;
        }
        return res % c;
    }

    /**
     * Main function
     * 
     * @param args [description]
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Prueba de primalidad de SolovayStrassen\n");
        /** Make an object of SolovayStrassen class **/
        SolovayStrassen ss = new SolovayStrassen();
        /** Accept number **/
        System.out.println("Ingresa un número:\n");
        long num = scan.nextLong();
        /** Accept number of iterations **/
        System.out.println("\nNúmero de iteraciones");
        int k = scan.nextInt();
        /** check if prime **/
        boolean prime = ss.isPrime(num, k);
        if (prime)
            System.out.println("\n" + num + " es primo");
        else
            System.out.println("\n" + num + " es compuesto");
    }
}