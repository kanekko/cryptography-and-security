import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.lang.*;

public class Caesar { 

    static String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz"; 
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * E(x) = x+n (mod N)
     */
    private static String codificar(String cadena, int desplazamiento){
        String rtnCadena = ""; 

        for(int i=0; i<cadena.length(); i++){

            if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) ||
                 alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ) {
                
                char cipherChar;

                if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) ) {

                    int x  = alfabetoMinusculas.indexOf(cadena.charAt(i));
                    // E(x)=  x +        n             (mod N)
                    int Ex = (x + desplazamiento) % alfabetoMinusculas.length();
                    
                    cipherChar = alfabetoMinusculas.charAt(Ex);
                    
                }else{

                    int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                    // E(x)=  x +        n             (mod N)
                    int Ex = (x + desplazamiento) % alfabetoMayusculas.length();
                    
                    cipherChar = alfabetoMayusculas.charAt(Ex);
                }

                rtnCadena += cipherChar; 

            }else{
                rtnCadena += cadena.charAt(i); 
            }

        } 

        return rtnCadena;
    }

    /**
     * D(x) = x-n (mod N)
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
     * Método main
     */
    public static void main(String[] arg){ 
        String cadena           = "Esta es una cadena de pureba.";
        int desplazamiento      = 3;

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