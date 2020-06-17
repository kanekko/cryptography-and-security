import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Rabin2 {
    private static Random r         = new SecureRandom();
    private static BigInteger TWO   = BigInteger.valueOf(2);
    private static BigInteger THREE = BigInteger.valueOf(3);
    private static BigInteger FOUR  = BigInteger.valueOf(4);

    /**
     * Generate a random blum prime ( a prime such that p≡3 (mod 4) )
     * @param bitLength number of bits in the prime
     * @return a random blum prime
     */
    public static BigInteger blumPrime(int bitLength) {
        BigInteger p;
        
        do {
            p = BigInteger.probablePrime(bitLength,r);
        }
        while( !p.mod(FOUR).equals(THREE) ); // congruent 3 (mod 4)

        return p;
    }

    /**
     * Generate a blum public and private key (more efficient decryption) with a specified number of bits
     * @param bitLength Number of bits in public key
     * @return An array of BigIntegers {n,p,q}. n is the public key and p and q are the private keys
     */
    public static BigInteger[] genKey(int bitLength) {
        BigInteger p = blumPrime(bitLength/2);
        BigInteger q = blumPrime(bitLength/2);
        BigInteger n = p.multiply(q);

        return new BigInteger[]{n,p,q};
    }

    /**
     * Encrypt a value with the public key
     * ie. C = E(m) = m^2(mod n)
     * @param m the value to encrypt
     * @param n the public key, n
     * @return c, the encrypted value
     */
    public static BigInteger encrypt(BigInteger m, BigInteger n) {
        // E(m)=m^2(mod n)
        return m.modPow(TWO, n);
    }

    /**
     * Decrypt a value with the private key (assumes blum key for fast decryption)
     * ie C(mod n)
     * @param c encrypted number
     * @param p private key, p
     * @param q private key, q
     * @return array of the 4 decryption possibilities
     */
    public static BigInteger[] decrypt(BigInteger c, BigInteger p , BigInteger q) {
        BigInteger N = p.multiply(q);

        BigInteger m_p1 = c.modPow(p.add(BigInteger.ONE).divide(FOUR), p);
        BigInteger m_p2 = p.subtract(m_p1);
        BigInteger m_q1 = c.modPow(q.add(BigInteger.ONE).divide(FOUR), q);
        BigInteger m_q2 = q.subtract(m_q1);

        BigInteger[] ext = ext_gcd(p,q); // Greatest common divisor

        BigInteger y_p = ext[1];
        BigInteger y_q = ext[2];

        //y_p*p*m_q + y_q*q*m_p (mod n)
        BigInteger d1 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p1)).mod(N);
        BigInteger d2 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p1)).mod(N);
        BigInteger d3 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p2)).mod(N);
        BigInteger d4 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p2)).mod(N);

        return new BigInteger[]{d1,d2,d3,d4};
    }


    /**
     * Greatest common divisor
     * maximo comun divison
     * @param a
     * @param b
     * @return
     */
    public static BigInteger[] ext_gcd(BigInteger a, BigInteger b) {
        BigInteger s = BigInteger.ZERO;
        BigInteger old_s = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;
        BigInteger old_t = BigInteger.ZERO;
        BigInteger r = b;
        BigInteger old_r = a;
        while(!r.equals(BigInteger.ZERO)) {
            BigInteger q = old_r.divide(r);
            BigInteger tr = r;
            r = old_r.subtract(q.multiply(r));
            old_r=tr;

            BigInteger ts = s;
            s = old_s.subtract(q.multiply(s));
            old_s=ts;

            BigInteger tt = t;
            t = old_t.subtract(q.multiply(t));
            old_t=tt;
        }
        //gcd, x,y
        //x,y such that ax+by=gcd(a,b)
        return new BigInteger[]{old_r, old_s, old_t};
    }


    public static void main(String[] args) {
        int counter    = 0;
        boolean worked =  false;

        BigInteger[] key = Rabin2.genKey(512);
        BigInteger N = key[0];
        BigInteger p = key[1];
        BigInteger q = key[2];
        System.out.println(N);
        System.out.println(p + "," + q);

        String s = "Esta es una prueba";
        BigInteger m = new BigInteger(s.getBytes(Charset.forName("ascii")));
        BigInteger c = Rabin2.encrypt(m, N);
        System.out.println("c:~>" + c);


        BigInteger[] m2 = Rabin2.decrypt(c, p, q);
        for (BigInteger b : m2) {
            String dec = new String(b.toByteArray(), Charset.forName("ascii"));
            System.out.println("M["+b+"]:~>" + dec);

            if (dec.equals(s)) {
                worked = true;
            }
        }
        if (worked)
            counter++;

    }

}