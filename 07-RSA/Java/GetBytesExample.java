import java.io.*;

public class GetBytesExample{
   public static void main(String args[]){
       String str = new String("HOLA");
       byte[] array1 = str.getBytes();
       System.out.println("Default Charset encoding:");

       for(byte b: array1){
           System.out.println(b);
       }
       
       // System.out.print("\nUTF-16 Charset encoding:");
       // try{
       //       byte [] array2 = str.getBytes("UTF-16");
       //       for(byte b1: array2){
       //          System.out.print(b1);
       //       }

       //       byte [] array3 = str.getBytes("UTF-16BE");
       //       System.out.print("\nUTF-16BE Charset encoding:");
       //       for(byte b2: array3){
       //          System.out.print(b2);
       //       }

       //       byte [] array4 = str.getBytes("US-ASCII");
       //       System.out.print("\nUS-ASCII Charset encoding:");
       //       for(byte b3: array4){
       //          System.out.print(b3);
       //       }

       //  }catch(UnsupportedEncodingException ex){
       //       System.out.println("Unsupported character set"+ex);
       //  }
   }	
}