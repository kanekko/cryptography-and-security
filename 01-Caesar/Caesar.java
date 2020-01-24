/**
 * 
 */
public class Caesar{ 

    // Member varialbes
    static String alphabetMinus = "abcdefghijklmnñopqrstuvwxyz";
    static String alphabetMayus = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

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

            if ( alphabetMinus.contains( String.valueOf(plainText.charAt(i)) ) || // Filtering characters 
                 alphabetMayus.contains( String.valueOf(plainText.charAt(i)) ) ) {
                
                char symbol; // temp char
                
                if ( alphabetMinus.contains( String.valueOf(plainText.charAt(i)) ) ) { // Minus case
                    // index of a char (in the loop)
                    int x  = alphabetMinus.indexOf(plainText.charAt(i));
                    // E(x)=  x +  n          (mod N)
                    int Ex = (x + key) % alphabetMinus.length();
                    // getting index of cipher char
                    symbol = alphabetMinus.charAt(Ex);
                }else{ // Mayus case
                    int x  = alphabetMayus.indexOf(plainText.charAt(i));
                    // E(x)=  x +  n          (mod N)
                    int Ex = (x + key) % alphabetMayus.length();
                    // getting index of cipher char
                    symbol = alphabetMayus.charAt(Ex);
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

            if ( alphabetMinus.contains( String.valueOf(cryptogram.charAt(i)) ) || // Filtering characters 
                 alphabetMayus.contains( String.valueOf(cryptogram.charAt(i)) ) ) {
                
                char symbol; // temp char

                if ( alphabetMinus.contains( String.valueOf(cryptogram.charAt(i)) ) ) { // Minus case
                    // index of a char (in the loop)
                    int x  = alphabetMinus.indexOf(cryptogram.charAt(i));
                    // D(x)=  x -  n         (mod N)
                    int Dx = (x - key) % alphabetMinus.length();
                    // getting index of decipher char
                    symbol = alphabetMinus.charAt(Dx);
                }else{ // Mayus case 
                    int x  = alphabetMayus.indexOf(cryptogram.charAt(i));
                    // D(x)=  x -  n         (mod N)
                    int Dx = (x - key) % alphabetMayus.length();
                    // getting index of decipher char
                    symbol = alphabetMayus.charAt(Dx);
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
        String plainText = "Esta es una prueba";
        // String plainText = "¡Hola! Las llaves de la casa te esperan escondidas bajo la maceta.";
        int displacement = 3; //key (maximun value is 27)

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
    }

}