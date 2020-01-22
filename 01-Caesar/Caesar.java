/**
 * 
 */
public class Caesar { 

    // Member varialbes
    static String alphabetMinus = "abcdefghijklmnñopqrstuvwxyz";
    static String alphabetMayus = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * E(x) = x+n (mod N)
     * 
     * @param cadena       plain text
     * @param displacement total of displacements
     * @return             string object with ciphertext
     */
    private static String encrypt(String plainText, int displacement){
        String rtnCadena = ""; 

        for(int i=0; i<plainText.length(); i++){

            if ( alfabetoMinusculas.contains( String.valueOf(plainText.charAt(i)) ) || // Filtering characters 
                 alfabetoMayusculas.contains( String.valueOf(plainText.charAt(i)) ) ) {
                
                char cipherChar;
                
                if ( alfabetoMinusculas.contains( String.valueOf(plainText.charAt(i)) ) ) { // Minus case
                    // index of a char (in loop)
                    int x  = alfabetoMinusculas.indexOf(plainText.charAt(i));
                    // E(x)=  x +        n                   (mod N)
                    int Ex = (x + displacement) % alfabetoMinusculas.length();
                    // getting index of chipher char
                    cipherChar = alfabetoMinusculas.charAt(Ex);
                
                }else{ // Mayus case

                    int x  = alfabetoMayusculas.indexOf(plainText.charAt(i));
                    // E(x)=  x +        n             (mod N)
                    int Ex = (x + displacement) % alfabetoMayusculas.length();
                    
                    cipherChar = alfabetoMayusculas.charAt(Ex);
                }

                rtnCadena += cipherChar; // Union of all chars

            }else{
                rtnCadena += plainText.charAt(i); 
            }

        } 

        return rtnCadena;
    }

    /**
     * D(x) = x-n (mod N)
     * 
     * @param cadena         ciphertext
     * @param desplazamiento total of displacements
     * @return               string object with plain text
     */
    private static String decrypt(String cadena, int desplazamiento){
        String rtnCadena = ""; 

        for(int i=0; i<cadena.length(); i++){

            if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) ||
                 alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ) {
                
                char cipherChar;

                if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) ) {

                    int x  = alfabetoMinusculas.indexOf(cadena.charAt(i));
                    // D(x)=  x -        n             (mod N)
                    int Dx = (x - desplazamiento) % alfabetoMinusculas.length();
                    
                    cipherChar = alfabetoMinusculas.charAt(Dx);
                    
                }else{

                    int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                    // D(x)=  x -        n             (mod N)
                    int Dx = (x - desplazamiento) % alfabetoMayusculas.length();
                    
                    cipherChar = alfabetoMayusculas.charAt(Dx);
                }

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
    public static void main(String[] args){
        String plainText = "Esta es una prueba";
        int displacement = 3;

        String cryptogram    = "";
        String decryptedText = "";

        // 1. Plain text
		System.out.println("Texto plano:     " + plainText);
		System.out.println();

		// 2. Encrypt
        cryptogram = encrypt(plainText, displacement);
        System.out.println("Texto codificado:   " + cryptogram);

        // 3. Decrypt
        decryptedText = decrypt(cryptogram, displacement);
        System.out.println("Texto decodificado: " + decryptedText);
    }

} 