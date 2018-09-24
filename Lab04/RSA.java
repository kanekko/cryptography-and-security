import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSA {

    /**
     * Método principal
     */
    public static void main(String[] args) throws Exception{

        // 1. Generar llaves prublicas y privadas
        KeyPair keyPair       = GenerarLlaves();
        PublicKey pubKey      = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 2. Codificar
        byte [] mensajeEncriptado = encriptar(privateKey, "Per aspera ad astra");     
        System.out.println(new String(mensajeEncriptado)); 

        // 3. Decodificar
        byte[] mensajeDescifrado = descifrar(pubKey, mensajeEncriptado);                                 
        System.out.println(new String(mensajeDescifrado)); 
    }

    /**
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair GenerarLlaves() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    /**
     * 
     * @param privateKey
     * @param message
     * @return
     * @throws Exception
     */
    public static byte[] encriptar(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    /**
     * 
     * @param publicKey
     * @param encrypted
     * @return
     * @throws Exception
     */
    public static byte[] descifrar(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }

}