public class Affine { 

   public static void main(String[] args) {
       String cadena = "esta es una cadena de prueba";
       int a = 5;
       int b = 15;

       String cadenaCifrada = cifrar(cadena, a, b);
       System.out.println("Texto claro:   " + cadena);
       System.out.println("Texto cifrado: " + cadenaCifrada);
   }
   

   /**
    * c = (am + b) mod n
    * @param  cadena [description]
    * @param  a      [description]
    * @param  b      [description]
    * @return        [description]
    */
   public static String cifrar(String cadena, int a, int b) {
       String str = "";

       for (int i=0; i<cadena.length(); i++) {
           
           char singleCharacter = cadena.charAt(i);
           
           if (Character.isLetter(singleCharacter)) { //filtro, solo letras
               // (ax + b) % 27
               singleCharacter = (char) ( (a * (int)(singleCharacter + 'a') + b) % 27 + 'a'); 
           }
           
           str +=singleCharacter;
       }

       return str;
   }

} 