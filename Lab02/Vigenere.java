public class Vigenere {

    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * E(x) = (Xi + Ki) mod L
     */
    private static String codificar(String cadena, String llave) {
        String rtnCadena = "";

        for (int i=0, j=0, l=cadena.length(); i<l; i++, j=j%l) {
            if( alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ){
                int Xi = alfabetoMayusculas.indexOf(cadena.charAt(i));
                int Ki = alfabetoMayusculas.indexOf(llave.charAt(j));
                // E(x)= ( Xi + Ki ) mod L
                int Ex = ( Xi + Ki ) % alfabetoMayusculas.length();
                char cipherChar = alfabetoMayusculas.charAt(Ex);

                rtnCadena += cipherChar; 
            }else{
                rtnCadena += cadena.charAt(i); 
            }
        }

        return rtnCadena;
    }

    /**
     * D(x) = (Ci + Ki) mod L
     */
    private static String decodificar(String cadena, String llave) {
        String rtnCadena = "";

        for (int i=0, j=0, l=cadena.length(); i<l; i++, j=j%l) {
            if( alfabetoMayusculas.contains( String.valueOf(cadena.charAt(i)) ) ){

                int Ci = alfabetoMayusculas.indexOf(cadena.charAt(i));
                int Ki = alfabetoMayusculas.indexOf(llave.charAt(j));
                int Dx = 0;
                
                if( (Ci-Ki)>=0 ){
                    // D(x)= ( Ci - Ki ) mod L
                    Dx = ( Ci - Ki ) % alfabetoMayusculas.length();
                }else{
                    // D(x)= ( Ci - Ki + L ) mod L
                    Dx = ( Ci - Ki + l ) % alfabetoMayusculas.length();
                }

                char cipherChar = alfabetoMayusculas.charAt(Dx);

                rtnCadena += cipherChar; 


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
        // String mensaje = "PER ASPERA AD ASTRA";
        // String llave   = "VIGENERE";
        String mensaje = "ATTACKATDAWN";
        String llave   = "LEMON";

        // 1. Texto original
		System.out.println("Texto original:     " + mensaje);
		System.out.println("Llave:              " + llave);
		System.out.println();        
		// 2. Codificar
        String mensajeCodificado = codificar(mensaje, llave); 
        System.out.println("Texto codificado:   " + mensajeCodificado); 
        // 3. Decodificar
        String cadenaDecodificada = decodificar(mensajeCodificado, llave); 
        System.out.println("Texto decodificado: " + cadenaDecodificada); 
    }

}