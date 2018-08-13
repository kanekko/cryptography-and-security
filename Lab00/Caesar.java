public class Caesar { 

    public static void main(String[] arg){ 

        String Cadena        = "Esta es una cadena de prueba"; 
        String CadenaCifrada = ""; 
        int Desplazamiento   = 3; 

		System.out.println(Cadena);
		
        CadenaCifrada = cifrar(Cadena, Desplazamiento); 
        System.out.println(CadenaCifrada); 
    } 

    private static String cifrar(String Cadena, int Desplazamiento){ 
        String alfabetoMinusculas = "abcdefghijklmnñopqrstuvwxyz";
        String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 
        String rtnCadena = ""; 

        for(int i=0; i<Cadena.length(); i++){ 
            if( (alfabetoMinusculas.indexOf(Cadena.charAt(i)) != -1) || (alfabetoMayusculas.indexOf(Cadena.charAt(i)) != -1) ) 
                rtnCadena += (alfabetoMinusculas.indexOf(Cadena.charAt(i)) != -1) 
                            ? alfabetoMinusculas.charAt(  ( alfabetoMinusculas.indexOf(Cadena.charAt(i)) +Desplazamiento)%alfabetoMinusculas.length() ) 
                            : alfabetoMayusculas.charAt(  ( alfabetoMayusculas.indexOf(Cadena.charAt(i)) +Desplazamiento)%alfabetoMayusculas.length() ); 
            else 
                rtnCadena += Cadena.charAt(i); 
        } 

        return rtnCadena;
    }

} 