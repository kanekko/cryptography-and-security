import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.lang.*;

import com.sun.xml.internal.org.jvnet.fastinfoset.sax.PrimitiveTypeContentHandler;

public class Caesar { 

    static String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz"; 
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 
    static BufferedReader bf         = new BufferedReader(new InputStreamReader(System.in));

    /**
     * E(x) = x+n (mod N)
     */
    private static String codificar(String cadena, int desplazamiento){
        String rtnCadena = ""; 

        for(int i=0; i<cadena.length(); i++){

            if ( alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) || 
                 alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ) {
                // Ejemplo con dezplacamiento = 3
                // abcdefghijklmnñopqrstuvwxyz
                // defghijklmnñopqrstuvwxyzabc
                rtnCadena += alfabetoMinusculas.contains( String.valueOf(cadena.charAt(i)) ) 
                            //                    E(x) =                        x                      +        n             (mod N)
                            ? alfabetoMinusculas.charAt(  alfabetoMinusculas.indexOf(cadena.charAt(i)) + desplazamiento % alfabetoMinusculas.length() )
                            //                    E(x) =                        x                      +        n             (mod N)
                            : alfabetoMayusculas.charAt(  alfabetoMayusculas.indexOf(cadena.charAt(i)) + desplazamiento % alfabetoMayusculas.length() );
            }else{
                rtnCadena += cadena.charAt(i); 
            }

        } 

        return rtnCadena;
    }



    public static void main(String[] arg){ 
        String cadena           = "WIKIPEDIA";
        int desplazamiento      = 6;
        String cadenaCodificada = ""; 
        // 1.
		System.out.println("Texto original:   " + cadena);
		// 2.
        cadenaCodificada = codificar(cadena, desplazamiento); 
        System.out.println("Texto codificado: " + cadenaCodificada); 
    } 

} 