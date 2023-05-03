import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HashGenerator {

  public byte[] hashing(String inpuString, String hashString, String sal) {
  
    String inputString = inpuString+sal;
    String sha256Hash = "";
    byte[] hashBytes = null;

    try {
      // Obtener la instancia de MessageDigest con el algoritmo SHA-256
      MessageDigest sha256 = MessageDigest.getInstance(hashString);
      
      // Convertir la cadena de entrada en un arreglo de bytes
      byte[] inputBytes = inputString.getBytes();
      
      // Generar el hash de la cadena
      hashBytes = sha256.digest(inputBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hashBytes;
  }
  public boolean hashComparator(byte[] expectedHash, byte[] actualHash){
    if (Arrays.equals(expectedHash, actualHash)) {
        System.out.println("Los hashes coinciden.");
        return true;
      } else {
        return false;
      }
  }

}


  
  
