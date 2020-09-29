/**
 * Class to implement Caesar cipher
 * in this implementation I used ALPHABET_MINUS and ALPHABET_MAYUS becase the 
 * range of A-Z in Ascii and Utf-8 don't have Ñ char
 * 
 * @author Canek García
 */
import java.lang.Math;

public class Caesar{ 

    // Member varialbes (Constants)
    static String ALPHABET_MINUS = "abcdefghijklmnñopqrstuvwxyz";
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    /**
     * E(x) = x+n (mod N)
     * 
     * @param plainText plain text
     * @param key       total of displacements (key of cryptosystem)
     * @return          string object with ciphertext (cryptogram)
     */
    private static String encrypt(String plainText, int key){
        String cryptogram = ""; 

        for(int i=0; i<plainText.length(); i++){

            if ( ALPHABET_MINUS.contains( String.valueOf(plainText.charAt(i)) ) || // Filtering characters 
                 ALPHABET_MAYUS.contains( String.valueOf(plainText.charAt(i)) ) ) {
                
                char symbol; // Symbol to get a char in the for loop
                
                if ( ALPHABET_MINUS.contains( String.valueOf(plainText.charAt(i)) ) ) { // Minus case
                    // index of a char (in the loop)
                    int x  = ALPHABET_MINUS.indexOf(plainText.charAt(i));
                    // E(x)=  x +  n          (mod N)
                    int Ex = (x + key) % ALPHABET_MINUS.length();
                    // getting index of cipher char
                    symbol = ALPHABET_MINUS.charAt(Ex);
                } else { // Mayus case
                    int x  = ALPHABET_MAYUS.indexOf(plainText.charAt(i));
                    // E(x)=  x +  n          (mod N)
                    int Ex = (x + key) % ALPHABET_MAYUS.length();
                    // getting index of cipher char
                    symbol = ALPHABET_MAYUS.charAt(Ex);
                }

                cryptogram += symbol; // Union of all chars

            }else{
                cryptogram += plainText.charAt(i); 
            }

        } 

        return cryptogram;
    }

    /**
     * D(x) = x-n (mod N)
     * 
     * @param cryptogram ciphered text
     * @param key        total of displacements (key of cryptosystem)
     * @return           string object with plain text (decoded text)
     */
    private static String decrypt(String cryptogram, int key){
        String plainText = ""; 

        for(int i=0; i<cryptogram.length(); i++){

            if ( ALPHABET_MINUS.contains( String.valueOf(cryptogram.charAt(i)) ) || // Filtering characters 
                 ALPHABET_MAYUS.contains( String.valueOf(cryptogram.charAt(i)) ) ) {
                
                char symbol; // Symbol to get a char in the for loop

                if ( ALPHABET_MINUS.contains( String.valueOf(cryptogram.charAt(i)) ) ) { // Minus case
                    // index of a char (in the loop)
                    int x  = ALPHABET_MINUS.indexOf(cryptogram.charAt(i));
                    // D(x)=  x -  n         (mod N)
                    //int Dx = ((x - key) % ALPHABET_MINUS.length());
                    int Dx = Math.floorMod( (x-key) , ALPHABET_MINUS.length() );
                    // getting index of decipher char
                    symbol = ALPHABET_MINUS.charAt(Dx);
                } else { // Mayus case 
                    int x  = ALPHABET_MAYUS.indexOf(cryptogram.charAt(i));
                    // D(x)=  x -  n         (mod N)
                    // int Dx = (x - key) % ALPHABET_MAYUS.length();
                    int Dx = Math.floorMod( (x-key) , ALPHABET_MAYUS.length() );
                    // getting index of decipher char
                    symbol = ALPHABET_MAYUS.charAt(Dx);
                }

                plainText += symbol; 

            }else{
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
        // String plainText = "a-z";
        // String plainText = "Per Aspera Ad Astra";
        String plainText = "¡Hola! Las llaves de la casa te esperan escondidas bajo la maceta.";
        int displacement = 5; //key (maximun value is 27)

        String cryptogram  = "";
        String decodedText = "";

        // 1. Plain text
		System.out.println("Texto plano: " + plainText);
		System.out.println();

		// 2. Encrypt
        cryptogram = encrypt(plainText, displacement);
        System.out.println("Criptograma:        " + cryptogram);

        // 3. Decrypt
        decodedText = decrypt(cryptogram, displacement);
        System.out.println("Texto decodificado: " + decodedText);
        System.out.println("");

        // 4. Cryptanalysis
        System.out.println("Criptoanálisis:");
        for(int i=0; i<ALPHABET_MINUS.length(); i++){
			decodedText = decrypt("¡Mtpf! Pfx ppfajx ij pf hfxf yj jxujwfr jxhtrinifx gfñt pf qfhjyf.", i);
            System.out.println(i+":~>" + decodedText);
        }
    }

}