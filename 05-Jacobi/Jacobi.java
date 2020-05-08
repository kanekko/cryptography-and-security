/**
 * Class to implement Jacobi symbol
 *
 * @see <a href="https://en.wikipedia.org/wiki/Jacobi_symbol">Jacobi symbol</a>
 */
public class Jacobi{
    
    /**
     * Function to calculate Jacobi symbol (a/n)
     * @param  a a integer >= 3
     * @param  n any positive odd integer
     * @return   Jacobi symbol
     */
    public static long JacobiSymbol(long a, long n) {

        if (n<=0 || n%2==0)
            return 0;
        
        long j = 1L; // temporal

        if (a < 0) {
            a = -a;
            if (n % 4 == 3)
                j = -j;
        }

        while (a != 0) {
            while (a % 2 == 0) {
                a /= 2;
                if (n % 8 == 3 || n % 8 == 5)
                    j = -j;
            }

            long temp = a; // other termporal
            a = n;
            n = temp;

            if (a%4==3 && n%4==3)
                j = -j;
            a %= n;
        }

        if (n == 1)
            return j;

        return 0;
    }

    /**
     * Main method 
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        long a = 1001; // 'a' a integer 
        long n = 9907; // 'n' any positive odd integer

        long jacobi = JacobiSymbol(a,n);

        System.out.println(jacobi);
    }

}