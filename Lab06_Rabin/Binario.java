public class Binario {

    int decimal;

    public int BinarioDecimal(String numero) {
        decimal = Integer.parseInt(numero, 2);
        return decimal;
    }

    public String decimalABinario(int numeroDecimal) {
        int temp = numeroDecimal;
        String resultado = "";
        while (temp != 0) {
            if (temp % 2 == 0) {
                resultado = "0" + resultado;
            } else {
                resultado = "1" + resultado;
            }
            temp = temp / 2;
        }
        return resultado;
    }
}