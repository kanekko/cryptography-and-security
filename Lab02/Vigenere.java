public class Vigenere {

    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * E(x) = (Xi + Ki) mod L
     */
    private static String codificar(String cadena, String llave) {
        String rtnCadena = "";

        for (int i=0; i<cadena.length(); i++) {
            if( alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ){
                int x  = alfabetoMayusculas.indexOf(cadena.charAt(i));
                // E(x) = (Xi + Ki) mod L
                int Ex = ( x + b) % alfabetoMayusculas.length();
                // char cipherChar = alfabetoMayusculas.charAt(Ex);

                // rtnCadena += cipherChar; 
            }else{
                rtnCadena += cadena.charAt(i); 
            }
        }

        return rtnCadena;
    }

    /**
     * Método principal
     */
    public static void main(String[] args) {
        String mensaje = "PER ASPERA AD ASTRA";
        String llave   = "VIGENERE";

        // 1. Texto original
		System.out.println("Texto original:     " + mensaje);
		System.out.println("Llave:              " + llave);
		System.out.println();        
		// 2. Codificar
        String mensajeCodificado = codificar(mensaje, llave); 
        System.out.println("Texto codificado:   " + mensajeCodificado); 
        // 3. Decodificar
        // String cadenaDecodificada = decodificar(cadenaCodificada, a, b); 
        // System.out.println("Texto decodificado: " + cadenaDecodificada); 
    }

}