
public class Vigenere {

    // Member viables (Constants)
    static String ALPHABET_MINUS = "abcdefghijklmnñopqrstuvwxyz";
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    /**
     * E(x) = (Xi + Ki) mod L
     */
    private static String encrypt(String plainText, String key) {
        String cryptogram = "";

        for (int i=0, j=0, l=plainText.length(), m=key.length(); i<l; i++, j=i%m) {

            if( ALPHABET_MAYUS.contains( String.valueOf(plainText.charAt(i)) ) ){
                // System.out.println("i:"+i);
                // System.out.println("j:"+j);
                int Xi = ALPHABET_MAYUS.indexOf(plainText.charAt(i));
                int Ki = ALPHABET_MAYUS.indexOf(key.charAt(j));
                // System.out.println("Xi:"+Xi);
                // System.out.println("Ki:"+Ki);
                
                // E(x)= ( Xi + Ki ) mod L
                int Ex = ( Xi + Ki ) % ALPHABET_MAYUS.length();

                char symbol = ALPHABET_MAYUS.charAt(Ex);
                cryptogram += symbol; 
            }else{
                cryptogram += plainText.charAt(i); 
            }

        }

        return cryptogram;
    }

    /**
     * When: (Ci - Ki) >= 0
     *     then: D(x) = (Ci - Ki) mod L
     * when: (Ci - Ki) < 0
     *     then: D(x) = (Ci - Ki + l) mod L 
     */
    private static String decrypt(String cryptogram, String key) {
        String plainText = "";

        for (int i=0, j=0, l=cryptogram.length(), m=key.length(); i<l; i++, j=i%m) {

            if( ALPHABET_MAYUS.contains( String.valueOf(cryptogram.charAt(i)) ) ){
                int Ci = ALPHABET_MAYUS.indexOf(cryptogram.charAt(i));
                int Ki = ALPHABET_MAYUS.indexOf(key.charAt(j));
                int Dx = 0;
                char symbol;

                if( (Ci-Ki)>=0 ){
                    // D(x)= ( Ci - Ki ) mod L
                    Dx = ( Ci - Ki ) % ALPHABET_MAYUS.length();
                }else if( (Ci-Ki)<0 ){
                    // D(x)= ( Ci - Ki + L ) mod L
                    Dx = ( Ci - Ki + ALPHABET_MAYUS.length() ) % ALPHABET_MAYUS.length();
                }

                symbol = ALPHABET_MAYUS.charAt(Dx);
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
     * @param args argumentos
     */
    public static void main(String[] args) {
        // String plainText = "ATTACKATDAWN";
        // String key       = "LEMON";
        String plainText = "PARISVAUTBIENUNEMESSE";
        String key       = "LOUP";

        String cryptogram  = "";
        String decodedText = "";

        // 1. Plain Text
		System.out.println("Texto plano:        " + plainText);
		System.out.println("Clave:              " + key);
		System.out.println();        

		// 2. Encrypt
        cryptogram = encrypt(plainText, key); 
        System.out.println("Criptograma:        " + cryptogram); 

        // 3. Decrypt
        decodedText = decrypt(cryptogram, key); 
        System.out.println("Texto decodificado: " + decodedText); 
    }

}