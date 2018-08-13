public class Caesar { 

    public static void main(String[] arg){ 
        String Cadena        = "Todo lo que se preguntaba eran las mismas respuestas que buscamos el resto de nosotros. ¿De dónde vengo? ¿A dónde voy? ¿Cuánto tiempo tengo? Todo lo que pude hacer fue sentarme y ver como moría."; 
        String CadenaCifrada = ""; 
        int Desplazamiento   = 3; 

		System.out.println(Cadena);
		
        CadenaCifrada = cifrar(Cadena, Desplazamiento); 
        System.out.println(CadenaCifrada); 
        // descifrar(CadenaCifrada, Desplazamiento);
    } 


    public static String cifrar(String Cadena, int Desplazamiento){ 
        int VInt        = 0; 
        String CCifrado = ""; 

        for (int i=0; i<Cadena.length();i++){ 
            if( (Cadena.codePointAt(i)>=65 && Cadena.codePointAt(i)<=90) 
			    || (Cadena.codePointAt(i)>=97 && Cadena.codePointAt(i)<=122) ){ 
                VInt= Cadena.codePointAt(i) + Desplazamiento;             
                CCifrado = CCifrado + new Character((char) VInt).toString(); 
            }else{ 
                CCifrado=CCifrado+ Cadena.charAt(i); 
            } 
        } 

        return CCifrado; 
    } 

	/*
    public static void descifrar(String Cadena, int Desplazamiento){ 
        int VInt=0; 
        String DCifrado=""; 

        for (int i=0; i<Cadena.length();i++){ 
            if((Cadena.codePointAt(i)>=65 && Cadena.codePointAt(i)<=90) || (Cadena.codePointAt(i)>=97 && Cadena.codePointAt(i)<=122) ){ 
                VInt= Cadena.codePointAt(i) - Desplazamiento;             
                DCifrado = DCifrado + new Character((char) VInt).toString(); 
            }else{ 
                DCifrado=DCifrado+ Cadena.charAt(i); 
            } 
        } 

        System.out.println(DCifrado); 
    } 
	*/

} 