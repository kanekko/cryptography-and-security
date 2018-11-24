import java.math.*;

public class Affine { 

    static String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz"; 
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * E(x) = (ax + b) mod n
     */
    private static String codificar(String cadena, int a, int b) {
        String rtnCadena = ""; 

        for (int i=0; i<cadena.length(); i++) {

            if( alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ){
                int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                // E(x)= ( a x  + b)                 mod n
                int Ex = ((a*x) + b) % alfabetoMayusculas.length();
                char cipherChar = alfabetoMayusculas.charAt(Ex);

                rtnCadena += cipherChar; 
            }else{
                rtnCadena += cadena.charAt(i); 
            }
            
        }

        return rtnCadena;
    }

    /**
     * D(x) = ( a^-1 (x-b) ) mod n
     */
    private static String decodificar(String cadena, int a, int b){
        String rtnCadena = ""; 

        // 1. inverso multiplicativo
        int a_inv = 0;
        int flag = 0;
        for (int i = 0; i < 27; i++){
            flag = (a * i) % 27;
            if (flag == 1)
            {
                a_inv = i;
                // System.out.println(i);
            }
        }

        // 2. aplicando formula de decifrado
        for (int i=0; i<cadena.length(); i++) {

            if( alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ){
                int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                // int aInverse = Math.pow(a,-1);
                int modN = alfabetoMayusculas.length();
				int Ex = ( ((a_inv)*(x-b+modN))%modN ); //% alfabetoMayusculas.length();
                char cipherChar = alfabetoMayusculas.charAt(Ex);
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
    public static void main(String[] args) {
        String cadena = "AFFINE CIPHER";
        int a = 5;
        int b = 8;

        // 1. Texto original
		System.out.println("Texto original:     " + cadena);
		System.out.println();
		// 2. Codificar
        String cadenaCodificada = codificar(cadena, a, b); 
        System.out.println("Texto codificado:   " + cadenaCodificada); 
        // 3. Decodificar
        String cadenaDecodificada = decodificar(cadenaCodificada, a, b); 
        System.out.println("Texto decodificado: " + cadenaDecodificada); 

    }

}