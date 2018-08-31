import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.lang.*;

public class Caesar { 

    static String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz"; 
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 
    static BufferedReader bf         = new BufferedReader(new InputStreamReader(System.in));

    /**
     * E(x) = x+n (mod 27)
     */
    private static String codificar(String cadena, int desplazamiento){
        String rtnCadena = ""; 

        for(int i=0; i<cadena.length(); i++){ 

            CharSequence caPattern = cadena.charAt(i);
            
            if( (alfabetoMinusculas.indexOf(cadena.charAt(i)) != -1) || 
                (alfabetoMayusculas.indexOf(cadena.charAt(i)) != -1) ) {
//https://www.geeksforgeeks.org/searching-for-character-and-substring-in-a-string/

            }

            /*
            if( (alfabetoMinusculas.indexOf(cadena.charAt(i)) != -1) || 
                (alfabetoMayusculas.indexOf(cadena.charAt(i)) != -1) ) {
                // rtnCadena += (alfabetoMinusculas.indexOf(cadena.charAt(i)) != -1) 
                //             ? alfabetoMinusculas.charAt(  ( alfabetoMinusculas.indexOf(cadena.charAt(i)) +Desplazamiento)%alfabetoMinusculas.length() ) 
                //             : alfabetoMayusculas.charAt(  ( alfabetoMayusculas.indexOf(cadena.charAt(i)) +Desplazamiento)%alfabetoMayusculas.length() ); 
            }                            
            else{
            //     rtnCadena += cadena.charAt(i); 
            }
            */

        } 

        return rtnCadena;
    }



    public static void main(String[] arg){ 

        String cadena           = "Esta es una cadena de prueba";
        int desplazamiento      = 3;
        String cadenaCodificada = ""; 

		System.out.println("Texto original:   " + cadena);
		
        cadenaCodificada = codificar(cadena, desplazamiento); 
        System.out.println("Texto codificado: " + cadenaCodificada); 
    } 

} 