import java.util.*;

public class Decifrador {
    private HashGenerator hashGenerator = new HashGenerator();
    private boolean encontrada = false;
    private String hashDocumento; 
    private String sal;
    private String tipoHash;
    private String contraseniaFinal;
    
public Decifrador(String hashEntrante, String salEntrante, String hash){
    this.hashDocumento = hashEntrante;
    this.sal = salEntrante;
    this.tipoHash = hash;
}
public void constructorCombinaciones(char[] alfabeto,StringBuilder tmpPassword,int cantLetras ,int tamanioContrasenia) {
    if (tamanioContrasenia == 0) {
        String hashEncontrada = hashGenerator.hashing(tmpPassword.toString(), tipoHash, sal);
        encontrada = hashGenerator.hashComparator(hashDocumento, hashEncontrada);
        contraseniaFinal = tmpPassword.toString();
        return;
    }
    for (int pos = 0; pos < cantLetras && !encontrada; ++pos) {
        tmpPassword.append(alfabeto[pos]);
        constructorCombinaciones(alfabeto, tmpPassword, cantLetras, tamanioContrasenia - 1);
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


