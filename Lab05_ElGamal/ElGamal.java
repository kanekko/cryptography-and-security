import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Security of the ElGamal algorithm depends on the difficulty of computing discrete logs
 * in a large prime modulus
 *
 * - Theorem 1 : a in [Z/Z[p]] then a^(p-1) [p] = 1
 * - Theorem 2 : the order of an element split the order group
 */
public final class ElGamal { // TODO extends Cryptosystem

    public static BigInteger TWO = new BigInteger("2");

    /**
     * Generate the public key and the secret key for the ElGamal encryption.
     *
     * @param n key size
     */
    public static List<List<BigInteger>> KeyGen(int n) {
        // (a) take a random prime 'p' with getPrime() function. p = 2 * p' + 1 with prime(p') = true
        BigInteger p = getPrime(n, 40, new Random());
        // (b) take a random element in [Z/Z[p]]* (p' order)
        BigInteger g = randNum(p, new Random());
        BigInteger pPrime = p.subtract(BigInteger.ONE).divide(ElGamal.TWO);

        while (!g.modPow(pPrime, p).equals(BigInteger.ONE)) {
            if (g.modPow(pPrime.multiply(ElGamal.TWO), p).equals(BigInteger.ONE))
                g = g.modPow(TWO, p);
            else
                g = randNum(p, new Random());
        }

        // (c) take a random in [0, p' - 1]
        BigInteger a = randNum(pPrime.subtract(BigInteger.ONE), new Random());
        // (d) calculate K = g^a (mod p)
        BigInteger K = g.modPow(a, p);
        // secret key is (p, a) and public key is (p, g, K)
        List<BigInteger> sk = new ArrayList<>(Arrays.asList(p, a));
        List<BigInteger> pk = new ArrayList<>(Arrays.asList(p, g, K));
        // [0] = pk, [1] = sk
        return new ArrayList<>(Arrays.asList(pk, sk));
    }

    /**
     * Encrypt ElGamal
     *
     * @param (p,g,K) public key
     * @param message message
     */
    public static List<BigInteger> Encrypt(BigInteger p, BigInteger g, BigInteger K, BigInteger message) {
        BigInteger pPrime = p.subtract(BigInteger.ONE).divide(ElGamal.TWO);
        // TODO [0, N -1] or [1, N-1] ?
        BigInteger b = randNum(pPrime, new Random());
        // encrypt couple (g^b, m * K^b)
        // i.e. y1 and y2
        return new ArrayList<>(Arrays.asList(g.modPow(b, p), message.multiply(K.modPow(b, p))));
    }

    /**
     * Decrypt ElGamal
     *
     * @param (p,x) secret key
     * @param (gr,mhr) (g^r, m * h^r)
     * @return the decrypted message
     */
    public static BigInteger Decrypt(BigInteger p, BigInteger a, BigInteger y1, BigInteger y2) {
        BigInteger hr = y1.modPow(a, p);
        return y2.multiply(hr.modInverse(p)).mod(p);
    }

    /**
     * Return a prime p = 2 * p' + 1
     *
     * @param nb_bits   is the prime representation
     * @param certainty probability to find a prime integer
     * @param prg       random
     * @return p
     */
    public static BigInteger getPrime(int nb_bits, int certainty, Random prg) {
        BigInteger pPrime = new BigInteger(nb_bits, certainty, prg);
        // p = 2 * pPrime + 1
        BigInteger p = pPrime.multiply(TWO).add(BigInteger.ONE);

        while (!p.isProbablePrime(certainty)) {
            pPrime = new BigInteger(nb_bits, certainty, prg);
            p = pPrime.multiply(TWO).add(BigInteger.ONE);
        }
        return p;
    }

    /**
     * Return a random integer in [0, N - 1]
     *
     * @param N
     * @param prg
     * @return
     */
    public static BigInteger randNum(BigInteger N, Random prg) {
        return new BigInteger(N.bitLength() + 100, prg).mod(N);
    }

    public static void main(String[] args) {
        //1. getting public and private key in a list
        List<List<BigInteger>> pksk = ElGamal.KeyGen(200); 
        
        // public key (g,p,K)
        BigInteger p = pksk.get(0).get(0);
        BigInteger g = pksk.get(0).get(1);
        BigInteger K = pksk.get(0).get(2);
        
        // secret key (p, a)
        BigInteger p_sk = pksk.get(1).get(0);
        BigInteger a    = pksk.get(1).get(1);

        // 2. encrypt test
        System.out.println("Message : 9");
        
        List<BigInteger> encrypt = ElGamal.Encrypt(p, g, K, new BigInteger("9"));
        System.out.println("Encrypt: " + encrypt);
        
        // 3. decrypt test
        System.out.println( "Decrypted : " + ElGamal.Decrypt( p_sk, a, encrypt.get(0), encrypt.get(1) ) );
    }
}