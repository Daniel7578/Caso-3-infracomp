

public class Decifrador {
    private HashGenerator hashGenerator = new HashGenerator();
    private boolean encontrada = false;
    private byte[] hashDocumento; 
    private String sal;
    private String tipoHash;
    private String contraseniaFinal;
    
public Decifrador(byte[] hashEntrante, String salEntrante, String hash){
    this.hashDocumento = hashEntrante;
    this.sal = salEntrante;
    this.tipoHash = hash;
}
public void constructorCombinaciones(char[] alfabeto,StringBuilder tmpPassword,int cantLetras ,int tamanioContrasenia) { //1
    if (tamanioContrasenia == 0) {
        byte[] hashEncontrada = hashGenerator.hashing(tmpPassword.toString(), tipoHash, sal);
        encontrada = hashGenerator.hashComparator(hashDocumento, hashEncontrada);
        if (encontrada){
            contraseniaFinal = tmpPassword.toString();
        }
        return;
    }
    for (int pos = 0; pos < cantLetras && !encontrada; ++pos) {
        tmpPassword.append(alfabeto[pos]);
        constructorCombinaciones(alfabeto, tmpPassword, cantLetras, tamanioContrasenia - 1);//aqui
        tmpPassword.deleteCharAt(tmpPassword.length() - 1); 
    }
}
public boolean getEncontrada(){
    return this.encontrada;
}
public String getContraseniaFinal(){
    return this.contraseniaFinal;
}
}


