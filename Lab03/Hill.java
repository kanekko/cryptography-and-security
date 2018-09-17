import java.math.*;


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


        double raizDouble = Math.sqrt( clave.length() );
        if (raizDouble == (int)raizDouble){

            int raizInt = (int)raizDouble;
            rtnClave = new int[raizInt][raizInt];
            int flagClave = 0;
            for(int i=0; i<raizInt; i++){
                for(int j=0; j<raizInt; j++){
                    rtnClave[i][j] = alfabetoMayusculas.indexOf(clave.charAt(flagClave));
                    flagClave++;
                }
            }

        }
        else{ //no es exacto la raíz cuadrada del la clave
            System.out.println("Llave invalida, no se puede formar una matriz de NxN");
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