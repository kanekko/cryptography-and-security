/*
 * Copyright (c) 2019 Canek García
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import java.lang.Math;

/**
 * Implementation of Caesar cipher.
 * 
 * Note: In this implementation I used ALPHABET_MINUS and ALPHABET_MAYUS vars becase the 
 * range of A-Z in ASCII and UTF-8 don't have the Ñ char.
 * 
 * @author  Canek García
 * @version 1.0
 * @since   2019-08-01
 */
public class Caesar{ 

    // Member varialbes (Constants)
    static String ALPHABET_MINUS = "abcdefghijklmnñopqrstuvwxyz";
    static String ALPHABET_MAYUS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    /**
     * E(x) = x+n (mod N)
     * 
     * @param plainText plain text
     * @param key       total of displacements (key of cryptosystem)
     * @return          string object with ciphertext (cryptogram)
     */
    private static String encrypt(String plainText, int key){
        String cryptogram = ""; 

        for(int i=0; i<plainText.length(); i++){

            if ( ALPHABET_MINUS.contains( String.valueOf(plainText.charAt(i)) ) || // Filtering characters 
                 ALPHABET_MAYUS.contains( String.valueOf(plainText.charAt(i)) ) ) {
                
                char symbol; // Symbol to get a char in the for loop
                
                if ( ALPHABET_MINUS.contains( String.valueOf(plainText.charAt(i)) ) ) { // Minus case
                    // index of a char (in the loop)
                    int x  = ALPHABET_MINUS.indexOf(plainText.charAt(i));
                    // E(x)=  x +  n          (mod N)
                    int Ex = (x + key) % ALPHABET_MINUS.length();
                    // getting index of cipher char
                    symbol = ALPHABET_MINUS.charAt(Ex);
                } else { // Mayus case
                    // index of a char
                    int x  = ALPHABET_MAYUS.indexOf(plainText.charAt(i));
                    // E(x)=  x +  n          (mod N)
                    int Ex = (x + key) % ALPHABET_MAYUS.length();
                    // getting index of cipher char
                    symbol = ALPHABET_MAYUS.charAt(Ex);
                }

                cryptogram += symbol; // Union of all chars

            }else{ // Another characters
                cryptogram += plainText.charAt(i); 
            }

        } 

        return cryptogram;
    }

    /**
     * D(x) = x-n (mod N)
     * 
     * @param cryptogram ciphered text
     * @param key        total of displacements (key of cryptosystem)
     * @return           string object with plain text (decoded text)
     */
    private static String decrypt(String cryptogram, int key){
        String plainText = ""; 

        for(int i=0; i<cryptogram.length(); i++){

            if ( ALPHABET_MINUS.contains( String.valueOf(cryptogram.charAt(i)) ) || // Filtering characters 
                 ALPHABET_MAYUS.contains( String.valueOf(cryptogram.charAt(i)) ) ) {
                
                char symbol; // Symbol to get a char in the for loop

                if ( ALPHABET_MINUS.contains( String.valueOf(cryptogram.charAt(i)) ) ) { // Minus case
                    // index of a char (in the loop)
                    int x  = ALPHABET_MINUS.indexOf(cryptogram.charAt(i));
                    // D(x)=  x -  n         (mod N)
                    //int Dx = (x - key) % ALPHABET_MINUS.length();
                    int Dx = Math.floorMod( (x-key) , ALPHABET_MINUS.length() );
                    // getting index of decipher char
                    symbol = ALPHABET_MINUS.charAt(Dx);
                } else { // Mayus case 
                    // index of a char
                    int x  = ALPHABET_MAYUS.indexOf(cryptogram.charAt(i));
                    // D(x)=  x -  n         (mod N)
                    // int Dx = (x - key) % ALPHABET_MAYUS.length();
                    int Dx = Math.floorMod( (x-key) , ALPHABET_MAYUS.length() );
                    // getting index of decipher char
                    symbol = ALPHABET_MAYUS.charAt(Dx);
                }

                plainText += symbol; 

            }else{
                plainText += cryptogram.charAt(i); 
            }

        } 

        return plainText;
    }

    /**
     * Main method
     * 
     * @param args arguments
     */
    public static void main(String[] args){
        
        // String plainText = "a-z";
        String plainText = "Hola";
        // String plainText = "¡Hola! Las llaves de la casa te esperan escondidas bajo la maceta.";
        int displacement = 3; //key (maximun value is 27)

        String cryptogram  = "";
        String decodedText = "";

        // 1. Plain text
		System.out.println("Texto plano:                 " + plainText);
		System.out.println();

		// 2. Encrypt
        cryptogram = encrypt(plainText, displacement);
        System.out.println("Criptograma (Texto cifrado): " + cryptogram);

        // 3. Decrypt
        decodedText = decrypt(cryptogram, displacement);
        System.out.println("Texto descifrado:            " + decodedText);
        System.out.println();

        // 4. Cryptanalysis
        //System.out.println("Criptoanálisis:");
        //for(int i=0; i<ALPHABET_MINUS.length(); i++){
		//	decodedText = decrypt("¡Mtpf! Pfx ppfajx ij pf hfxf yj jxujwfr jxhtrinifx gfñt pf qfhjyf.", i);
        //    System.out.println(i+":~>" + decodedText);
        //}
    }

}