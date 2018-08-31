import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.lang.*;
// import com.sun.xml.internal.org.jvnet.fastinfoset.sax.PrimitiveTypeContentHandler;

public class Caesar { 

    static String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz"; 
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 
    // static BufferedReader bf         = new BufferedReader(new InputStreamReader(System.in));

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
     * Método main
     */
    public static void main(String[] arg){ 
        String cadena           = "Esta es una cadena de pureba.";
        int desplazamiento      = 3;
        String cadenaCodificada = ""; 
        // 1.
		System.out.println("Texto original:   " + cadena);
		// 2.
        cadenaCodificada = codificar(cadena, desplazamiento); 
        System.out.println("Texto codificado: " + cadenaCodificada); 
    } 

} 