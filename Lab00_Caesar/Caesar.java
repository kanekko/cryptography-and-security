import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.lang.*;

public class Caesar { 

    // Member varialbes
    static String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz";
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * E(x) = x+n (mod N)
     * 
     * @param cadena         plain text
     * @param desplazamiento total of displacements
     * @return               string object with ciphertext
     */
    private static String codificar(String cadena, int desplazamiento){
        String rtnCadena = ""; 

        for(int i=0; i<cadena.length(); i++){

            if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) || // Filtering characters 
                 alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ) {
                
                char cipherChar;
                
                if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) ) { // Minus case
                    // index of a char (in loop)
                    int x  = alfabetoMinusculas.indexOf(cadena.charAt(i));
                    // E(x)=  x +        n                   (mod N)
                    int Ex = (x + desplazamiento) % alfabetoMinusculas.length();
                    // getting index of chipher char
                    cipherChar = alfabetoMinusculas.charAt(Ex);
                
                }else{ // Mayus case

                    int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                    // E(x)=  x +        n             (mod N)
                    int Ex = (x + desplazamiento) % alfabetoMayusculas.length();
                    
                    cipherChar = alfabetoMayusculas.charAt(Ex);
                }

                rtnCadena += cipherChar; // Union of all chars

            }else{
                rtnCadena += cadena.charAt(i); 
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
    private static String decodificar(String cadena, int desplazamiento){
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
        String cadena      = "Esta es una cadena de pureba.";
        int desplazamiento = 3;

        String cadenaCodificada   = ""; 
        String cadenaDecodificada = ""; 

        // 1. Texto original
		System.out.println("Texto original:     " + cadena);
		System.out.println();
		// 2. Codificar
        cadenaCodificada = codificar(cadena, desplazamiento); 
        System.out.println("Texto codificado:   " + cadenaCodificada); 
        // 3. Decodificar
        cadenaDecodificada = decodificar(cadenaCodificada, desplazamiento); 
        System.out.println("Texto decodificado: " + cadenaDecodificada); 
    } 

} 