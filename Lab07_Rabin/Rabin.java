public class Rabin {

    private String mensaje;
    private long n, q, p;
    private String cifrado;
    private String CadenaCompleta;
    private int m, c;

    // gets and sets
    public int getM() {
        return m;
    }

    public int getC() {
        return c;
    }

    public String getCadenaCompleta() {
        return CadenaCompleta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public long getN() {
        return n;
    }

    public long getQ() {
        return q;
    }

    public long getP() {
        return p;
    }

    public String getCifrado() {
        return cifrado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * getting a prime number
     * @param n
     * @return
     */
    public boolean primo(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generate prime
     */
    public void GenerarPrimos() {
        Boolean resP, resQ;
        do {
            p = (int) (Math.random() * (1000 - 100 + 1) + 100);
            q = (int) (Math.random() * (1000 - 100 + 1) + 100);
            resP = primo((int) p);
            resQ = primo((int) q);
        } while ((p < q) || (p == q) || (resP == false) || (resQ == false) || ((p - 3) % 4 != 0) || ((q - 3) % 4 != 0));

        System.out.println("p:" + p);
        System.out.println("q:" + q);
    }

    /**
     * 
     */
    public void GenerarKey() {
        GenerarPrimos();
        n = p * q;
        System.out.println("n: " + n);
    }

    /**
     * if i neeed to complete a string o bits
     * @return
     */
    public String CompletarCadena() {
        String nuevo_mensaje, aux;
        int tam = mensaje.length();
        int diferencia = tam - (16 - tam);
        System.out.println("diferencia: " + diferencia);
        if (tam < 16) {
            aux = mensaje.substring(diferencia, tam);
            nuevo_mensaje = mensaje + aux;
        } else {
            nuevo_mensaje = mensaje;
        }
        return nuevo_mensaje;
    }

    /**
     * 
     * @return
     */
    public String Encriptar() {
        // Completando Cadena en el caso de que no cumplan los 16 bits
        CadenaCompleta = CompletarCadena();
        // Pasando la cadena a decimal para poder encriptar
        Binario objBinario = new Binario();
        m = objBinario.BinarioDecimal(CadenaCompleta);
        // Encriptando
        Exponenciacion expo = new Exponenciacion();
        c = expo.CalcularExp(m, 2, (int) n);
        // Pasando a Binario el mensaje cifrado
        cifrado = objBinario.decimalABinario(c);
        return cifrado;
    }

    /**
     * 
     * @param m
     * @param c
     * @return
     */
    public String[] Desencriptar(int m, int c) {
        RaizCompuesta obj = new RaizCompuesta();
        int raices[] = new int[4];
        Binario objBinario = new Binario();
        String Descifrado[] = new String[4];
        raices = obj.CalcularRaizCompuesta(m, c);
        if (raices == null) {
            JOptionPane.showMessageDialog(null, "NO EXISTEN RAICES");
        } else {

            Descifrado[0] = objBinario.decimalABinario(raices[0]);
            Descifrado[1] = objBinario.decimalABinario(raices[1]);
            Descifrado[2] = objBinario.decimalABinario(raices[2]);
            Descifrado[3] = objBinario.decimalABinario(raices[3]);
        }
        return Descifrado;
    }

    public static void main(String[] args) {
        
    }

}
