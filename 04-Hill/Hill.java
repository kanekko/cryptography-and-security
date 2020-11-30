/**
 * Class to implement Hill cipher 
 *
 * @author  Canek García
 * @version 1.0
 * @since   2019-08-01
 */
public class Hill{

    /**
     * I use this var because ASCII and UTF-8 don't have form A to Z with Ñ char inside of this range
     */
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    /**
     * Algorithm to encrypt
     * 
     * @param  plainText Plain text
     * @param  key       Key of cipher
     * @return           String ciphered with Hill cryptosystem
     */
    private static String encrypt(String plainText, String key) {
        String cryptogram = "";

        // 0. Calculate determinant and check if is invertible
        
        


        // 1. Construcción y verificación de la Matriz correspondiente a la clave
        // int[][] matrizClave = getMatrizClave(clave);

        // 2. Construcción de n-gramas del mensaje
        // int[][] matrizMensaje = getMatrizMensaje(mensaje, matrizClave.length);

        // 3. Multiplicamos Matrices 
        // int [][] claveXmensaje = multiplicaMatrices(matrizClave, matrizMensaje);

        // 4. Recuperamos mensaje ofuscado
        // cryptogram = getMessageCipher(claveXmensaje);

        return cryptogram;
    }

    /**
     * [getMatrizClave description]
     * @param  clave [description]
     * @return       [description]
     */
    private static int[][] getMatrizClave(String clave){
        int[][] rtnClave = new int[0][0];

        double raizDouble = Math.sqrt( clave.length() );
        if (raizDouble == (int)raizDouble){

            int raizInt = (int)raizDouble;
            rtnClave = new int[raizInt][raizInt];
            int flagClave = 0;
            for(int i=0; i<raizInt; i++){
                for(int j=0; j<raizInt; j++){
                    rtnClave[i][j] = ALPHABET_MAYUS.indexOf(clave.charAt(flagClave));
                    flagClave++;
                }
            }

        }
        else{ //no es exacto la raíz cuadrada del la clave
            System.out.println("Llave invalida, no se puede formar una matriz de NxN");
            // rtnClave[i][j] = null;
        }

        return rtnClave;
    }

    /**
     * NOTA: este método regresa la matriz de manera transpuesta, es decir:
     * [ 2  15 13 ]
     * [ 19 21 11 ]
     * en lugar de :
     * [ 2 19] 
     * [15 21] 
     * [13 11] 
     * debido a que es más fácil trabajar con la primer versión de matriz y 
     * por el mismo diseño del lenguaje Java.
     * @param mensaje
     * @return
     */
    private static int[][] getMatrizMensaje(String mensaje, int longitudeMatrizClave){
        int[][] rtnMensaje = new int[0][0];

        if( mensaje.length()%longitudeMatrizClave == 0 ){
            int rows = mensaje.length()/longitudeMatrizClave; //length of N-gramas
            rtnMensaje = new int[rows][longitudeMatrizClave]; //initializing matrix

            int flagPositionMesaje = 0;
            for(int i=0; i<rows; i++){ // i-rows
                for(int j=0; j<longitudeMatrizClave; j++){ // j-columns
                    rtnMensaje[i][j] = ALPHABET_MAYUS.indexOf(mensaje.charAt(flagPositionMesaje));
                    flagPositionMesaje++;
                }
            }

        }else{
            System.out.println("Mensaje invalido, no se puede formar matrices de Nx1");
        }

        return rtnMensaje;
    }

    /**
     * 
     * @param matrizClave
     * @param matrizMensaje
     * @return Matriz con operaciónes resueltas y el modulo calculado en cada posición.
     */
    private static int[][] multiplicaMatrices(int[][] matrizClave, int[][] matrizMensaje){
        int[][] rtnMultiplicacion = new int[matrizMensaje.length][matrizMensaje[0].length];
        
        for(int h=0; h<rtnMultiplicacion.length;h++){
            for(int i=0; i<matrizClave.length; i++){ // looping on rows of matrizMensaje
                int a = 0;
                for(int j=0; j<matrizMensaje[0].length; j++){
                    a = a + (matrizClave[i][j]*matrizMensaje[h][j]);
                }
                rtnMultiplicacion[h][i] = a % ALPHABET_MAYUS.length();
            }
        }

        return rtnMultiplicacion;
    }

    /**
     * 
     * @param matrizChiper
     * @return
     */
    private static String getMessageCipher(int[][] matrizChiper){
        String rtnMensaje = "";

        for(int i=0; i<matrizChiper.length; i++){
            for(int j=0; j<matrizChiper[i].length; j++){
                rtnMensaje = rtnMensaje + ( ALPHABET_MAYUS.charAt(matrizChiper[i][j]) + "");
            }
        }

        return rtnMensaje;
    }

    /**
     * TODO
     */
    private static String decrypt(String cadena, int[][] llave, int dimension) {
        String rtnCadena = "";


        return rtnCadena;
    }

    /**
     * Main method
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        // String plainText = "ACT";
        // String key       = "GYBNQKURP";
        // String plainText = "POH";
        // String key       = "GYBNQKURP";
        String plainText = "CONSUL";
        String key       = "FORTALEZA";

        // 1. Plain text
		System.out.println("Texto plano: " + plainText);
		System.out.println("Clave:       " + key);
        System.out.println();  

        // 2. Encrypt
        String cryptogram = encrypt(plainText, key);
        System.out.println("Criptograma: " + cryptogram);

        // 3. Decrypt
        // String decodedText = decipher(mensaje, matriz, 2);
        // System.out.println("Texto decifrado: " + plainText); 
    }

}