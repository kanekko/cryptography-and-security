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
                int Ex = ((a*x) + b) % alfabetoMayusculas.length();
                char cipherChar = alfabetoMayusculas.charAt(Ex);
                rtnCadena += cipherChar; 
            }else{
                rtnCadena += cadena.charAt(i); 
            }
            // char singleCharacter = cadena.charAt(i);
            // if (Character.isLetter(singleCharacter)) { //filtro, solo letras
            //     // (ax + b) % 27
            //     singleCharacter = (char) ( (a * (int)(singleCharacter + 'a') + b) % 27 + 'a'); 
            // }
            // str +=singleCharacter;

        }

        return rtnCadena;
    }


    /**
     * 
     */
    public static void main(String[] args) {
        String cadena = "ESTA ES UNA CADENA DE PRUEBA";
        int a = 17;
        int b = 5;

        // 1. Texto original
		System.out.println("Texto original:     " + cadena);
		System.out.println();
		// 2. Codificar
        String cadenaCodificada = codificar(cadena, a, b); 
        System.out.println("Texto codificado:   " + cadenaCodificada); 
        // 3. Decodificar
        // cadenaDecodificada = decodificar(cadenaCodificada, desplazamiento); 
        // System.out.println("Texto decodificado: " + cadenaDecodificada); 

    }

}