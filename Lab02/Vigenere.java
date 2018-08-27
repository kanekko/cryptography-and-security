public class Vigenere {
	
    public static void main(String[] args) {
        String key = "HOLA";
        String message = "ESTA ES LA PRUEBA";
        String enc = encrypt(message, key);

        System.out.println("Mensaje: " + message);
		System.out.println("Llave: " + key);		
        System.out.println("Vigenere: " + enc);
    }
 
    static String encrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) + 'A') % 27 + 'A');
        }
        return res;
    }
 
}