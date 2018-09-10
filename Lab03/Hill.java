public class Hill {

    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

    /**
     * 
     */
    private static String codificar(String cadena, int[][] llave, int dimension) {
        String rtnCadena = "";

        return rtnCadena;
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
        String llave   = "FORTALEZA";

        // 1. Texto original
		System.out.println("Texto original:     " + mensaje);
		System.out.println("Llave:              " + llave);
        System.out.println();  
              

        // 2. Codificar
        int[][] matriz = new int[3][2];        

        //NOTA: pueden agregar los métodos necesarios para obtener la matriz de la palabra clave

        String mensajeCodificado = codificar(mensaje, matriz, 2); 
        System.out.println("Texto codificado:   " + mensajeCodificado); 

        // 3. Decodificar
        String cadenaDecodificada = decodificar(mensaje, matriz, 2); 
        System.out.println("Texto decodificado: " + cadenaDecodificada); 
    }

}