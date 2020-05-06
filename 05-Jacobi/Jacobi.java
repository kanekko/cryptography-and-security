
public class Jacobi{


}



/** Function to calculate jacobi (a/b) **/
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