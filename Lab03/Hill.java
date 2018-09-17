public class Hill {

    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * 
     */
    private static String codificar(String clave, String mensaje) {
        String rtnCadena = "";

        //1. Verificar la longitud de la clave
        int[][] claveMatriz = getMatrizClave(clave);


        //2. 

        return rtnCadena;
    }


    private static int[][] getMatrizClave(String clave){
        int[][] rtnClave = new int[0][0];

        for(ini i=0; i<3;i++){
            for(ini j=0; i<3;j++){
                rtnClave[i][j] = alfabetoMayusculas.indexOf(cadena.charAt(i));
            }
        }

        return rtnClave;
    }


    /**
     * 
     */
    private static String decodificar(String cadena, int[][] llave, int dimension) {
        String rtnCadena = "";


        return rtnCadena;
    }


    /**
     * Método principal
     */
    public static void main(String[] args) {
        String mensaje = "CONSUL";
        String clave   = "FORTALEZA";

        // 1. Mensaje original
		System.out.println("Mensaje original: " + mensaje);
		System.out.println("Clave:            " + clave);
        System.out.println();  

        // 2. Codificar
        String mensajeCodificado = codificar(clave, mensaje);
        System.out.println("Mensaje codificado:   " + mensajeCodificado); 

        // 3. Decodificar
        // String cadenaDecodificada = decodificar(mensaje, matriz, 2); 
        // System.out.println("Texto decodificado: " + cadenaDecodificada); 
    }

}