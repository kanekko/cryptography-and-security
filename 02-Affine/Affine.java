/**
 * 
 */
import java.math.*;

public class Affine {

    static String ALPHABET_MINUS = "abcdefghijklmnñopqrstuvwxyz";
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    /**
     * E(x) = (ax + b) mod n
     */
    private static String codificar(String plainText, int a, int b) {
        String cryptogram = ""; 

        for (int i=0; i<plainText.length(); i++) {

            if( ALPHABET_MAYUS.contains(String.valueOf(plainText.charAt(i))) ){
                int x  = ALPHABET_MAYUS.indexOf(plainText.charAt(i));
                // E(x)= ( a x  + b)                 mod n
                int Ex = ((a*x) + b) % ALPHABET_MAYUS.length();
                char symbol = ALPHABET_MAYUS.charAt(Ex);

                cryptogram += symbol; 
            }else{
                cryptogram += plainText.charAt(i); 
            }
            
        }

        return cryptogram;
    }

    /**
     * D(x) = ( a^-1 (x-b) ) mod n
     */
    private static String decodificar(String cadena, int a, int b){
        String rtnCadena = ""; 

        // 1. inverso multiplicativo
        int a_inv = 0;
        int flag = 0;
        for (int i = 0; i < 27; i++){
            flag = (a * i) % 27;
            if (flag == 1)
            {
                a_inv = i;
            }
        }

        // 2. aplicando formula de decifrado
        for (int i=0; i<cadena.length(); i++) {

            if( alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ){
                int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                // int aInverse = Math.pow(a,-1);
                int modN = alfabetoMayusculas.length();
                //D(x) = (    a^-1  (x-  b) )  mod n
				int Dx = ( ((a_inv)*(x-b+modN))%modN ); //% alfabetoMayusculas.length();
                char cipherChar = alfabetoMayusculas.charAt(Dx);
                rtnCadena += cipherChar; 
            }else{
                rtnCadena += cadena.charAt(i); 
            }
        }

        return rtnCadena;
    }

    /**
     * Main method
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        String plainText = "Per Aspera Ad Astra";
        int a = 5;
        int b = 15;

        String cryptogram  = "";
        String decodedText = "";

        // 1. Pinting plain text
		System.out.println("Texto plano:     " + plainText);
		System.out.println();

		// 2. Encypt
        cryptogram = encrypt(plainText, a, b);
        System.out.println("Criptograma:   " + cryptogram);

        // 3. Decrypt
        decodedText = decrypt(cryptogram, a, b);
        System.out.println("Texto decodificado: " + decodedText);
    }

}