/**
 * Class to implement Affine cipher
 *
 * @author Canek García
 */
// import java.math.*;
import java.lang.Math;

public class Affine {

    // Member viables (Constants)
    static String ALPHABET_MINUS = "abcdefghijklmnñopqrstuvwxyz";
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    /**
     * E(x) = (ax + b) mod n
     */
    private static String encrypt(String plainText, int a, int b) {
        String cryptogram = ""; 

        for (int i=0; i<plainText.length(); i++) {

            if( ALPHABET_MINUS.contains( String.valueOf(plainText.charAt(i)) ) || 
                ALPHABET_MAYUS.contains( String.valueOf(plainText.charAt(i)) ) ) {

                char symbol;

                if( ALPHABET_MINUS.contains(String.valueOf(plainText.charAt(i))) ){
                    int x  = ALPHABET_MINUS.indexOf(plainText.charAt(i));
                    // E(x)= ( a x  + b)           mod n
                    int Ex = ((a*x) + b) % ALPHABET_MINUS.length();
                    symbol = ALPHABET_MINUS.charAt(Ex);
                } else {
                    int x  = ALPHABET_MAYUS.indexOf(plainText.charAt(i));
                    // E(x)= ( a x  + b)           mod n
                    int Ex = ((a*x) + b) % ALPHABET_MAYUS.length();
                    symbol = ALPHABET_MAYUS.charAt(Ex);
                }

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
    private static String decrypt(String cryptogram, int a, int b){
        String plainText = ""; 

        // 1. Getting mult inverse
        int a_inv = 0;
        int flag = 0;
        for (int i=0; i<ALPHABET_MINUS.length(); i++){
            flag = (a * i) % ALPHABET_MINUS.length();
            if (flag == 1)
                a_inv = i;
        }

        // 2. Building plain text
        for (int i=0; i<cryptogram.length(); i++) {

            if ( ALPHABET_MINUS.contains( String.valueOf(cryptogram.charAt(i)) ) || // Filtering characters 
                 ALPHABET_MAYUS.contains( String.valueOf(cryptogram.charAt(i)) ) ) {

                char symbol;

                if( ALPHABET_MINUS.contains( String.valueOf(cryptogram.charAt(i)) ) ){
                    int x  = ALPHABET_MINUS.indexOf(cryptogram.charAt(i));
                    // int aInverse = Math.pow(a,-1);
                    //int modN = ALPHABET_MINUS.length();
                    
                    //D(x) = (    a^-1  (x-  b) )  mod n
                    // int Dx = ( ((a_inv)*(x-b+modN))%modN ); //% ALPHABET_MINUS.length();
                    int Dx = Math.floorMod( (a_inv*(x-b)) , ALPHABET_MINUS.length() );

                    symbol = ALPHABET_MINUS.charAt(Dx);
                }else{
                    int x  = ALPHABET_MAYUS.indexOf(cryptogram.charAt(i));
                    int Dx = Math.floorMod( (a_inv*(x-b)) , ALPHABET_MAYUS.length() );
                    symbol = ALPHABET_MAYUS.charAt(Dx);
                }
                
                plainText += symbol; 
            } else {
                plainText += cryptogram.charAt(i);
            }

        }

        return plainText;
    }

    /**
     * Main method
     * 
     * @param args arguments
     */
    public static void main(String[] args){
        // String plainText = "b";
        // String plainText = "a-z";
        String plainText = "HOLA";
        // String plainText = "¡Hola! Las llaves de la casa te esperan escondidas bajo la maceta.";
        int a = 5;
        int b = 15;

        String cryptogram  = "";
        String decodedText = "";

        // 1. Plain text
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