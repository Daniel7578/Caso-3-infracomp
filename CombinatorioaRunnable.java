public class CombinatorioaRunnable extends Thread {
    private final char[] alfabeto;
    private final StringBuilder tmpPassword;
    private final int cantLetras;
    private final int tamanioMaximo;
    private final HashGenerator hashGenerator = new HashGenerator();
    private final String tipoHash;
    private final String sal;
    private final byte[] hashDocumento;
    private static boolean encontrada = false;
    private String contraseniaFinal;
    private char[] alfabetoCompleto;
    private int id;
    public CombinatorioaRunnable(int id, char[] alfabeto, StringBuilder tmpPassword, int cantLetras, int tamanioMaximo, String tipoHash, String sal, byte[] hashDocumento, char[] alfabetoCompleto) {
        this.alfabeto = alfabeto;
        this.tmpPassword = tmpPassword;
        this.cantLetras = cantLetras;
        this.tamanioMaximo = tamanioMaximo;
        this.tipoHash = tipoHash;
        this.sal = sal;
        this.hashDocumento = hashDocumento;
        this.contraseniaFinal = null;
        this.alfabetoCompleto = alfabetoCompleto;
        this.id = id;
    }
    @Override
    public void run() {
        for (int tamanioContrasenia = 1; tamanioContrasenia <= tamanioMaximo; tamanioContrasenia ++){
            constructorCombinaciones(alfabeto, tmpPassword, cantLetras, tamanioContrasenia, alfabetoCompleto);
        }
    }
    private void constructorCombinaciones(char[] alfabeto,StringBuilder tmpPassword,int cantLetras ,int tamanioContrasenia, char[]AlfabetoCompleto) {//3
        if (tamanioContrasenia == 0) {
        	synchronized (CombinatorioaRunnable.class) {
                if (encontrada){
                    return;
                }
                byte[] hashEncontrada = hashGenerator.hashing(tmpPassword.toString(), tipoHash, sal);
                encontrada = hashGenerator.hashComparator(hashDocumento, hashEncontrada);
                if (encontrada){
                System.out.println("ENCONTRADA POR: "+Integer.toString(id));

                contraseniaFinal = tmpPassword.toString();
                }
        	}
                return;
            
            
        }
        for (int pos = 0; pos < cantLetras && !encontrada; ++pos) {
            tmpPassword.append(alfabeto[pos]);
            constructorCombinaciones(alfabetoCompleto, tmpPassword, alfabetoCompleto.length, tamanioContrasenia - 1,alfabetoCompleto);
            tmpPassword.deleteCharAt(tmpPassword.length() - 1); 
        }
    }
    public String getContraseniaFinal() {
        return contraseniaFinal;
    }
    
    
}
