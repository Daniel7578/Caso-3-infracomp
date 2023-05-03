public class CombinacionesRunnable implements Runnable {
    private final char[] alfabeto;
    private final StringBuilder tmpPassword;
    private final int cantLetras;
    private final int tamanioMaximo;
    private final HashGenerator hashGenerator = new HashGenerator();
    private final String tipoHash;
    private final String sal;
    private final byte[] hashDocumento;
    private boolean encontrada;
    private String contraseniaFinal;

    public CombinacionesRunnable(char[] alfabeto, StringBuilder tmpPassword, int cantLetras, int tamanioMaximo, HashGenerator hashGenerator, String tipoHash, String sal, byte[] hashDocumento) {
        this.alfabeto = alfabeto;
        this.tmpPassword = tmpPassword;
        this.cantLetras = cantLetras;
        this.tamanioMaximo = tamanioMaximo;
        this.tipoHash = tipoHash;
        this.sal = sal;
        this.hashDocumento = hashDocumento;
        this.encontrada = false;
        this.contraseniaFinal = null;
    }

    @Override
    public void run() {
        for (int tamanioContrasenia = 1; tamanioContrasenia <= tamanioMaximo && !encontrada; tamanioContrasenia++) {
            constructorCombinaciones(alfabeto, tmpPassword, cantLetras, tamanioContrasenia);
        }
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public String getContraseniaFinal() {
        return contraseniaFinal;
    }

    private void constructorCombinaciones(char[] alfabeto, StringBuilder tmpPassword, int cantLetras, int tamanioContrasenia) {
        if (tamanioContrasenia == 0) {
            synchronized (this) {
                if (encontrada) {
                    return;
                }
                byte[] hashEncontrada = hashGenerator.hashing(tmpPassword.toString(), tipoHash, sal);
                encontrada = hashGenerator.hashComparator(hashDocumento, hashEncontrada);
                if (encontrada) {
                    contraseniaFinal = tmpPassword.toString();
                }
            }
            return;
        }
        for (int pos = 0; pos < cantLetras && !encontrada; pos++) {
            tmpPassword.append(alfabeto[pos]);
            constructorCombinaciones(alfabeto, tmpPassword, cantLetras,tamanioContrasenia);
        }
    }
}
