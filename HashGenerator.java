import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

  public String hashing(String inpuString, String hashString, String sal) {
  
    String inputString = inpuString+sal;
    String sha256Hash = "";

    try {
      // Obtener la instancia de MessageDigest con el algoritmo SHA-256
      MessageDigest sha256 = MessageDigest.getInstance(hashString);
      
      // Convertir la cadena de entrada en un arreglo de bytes
      byte[] inputBytes = inputString.getBytes();
      
      // Generar el hash de la cadena
      byte[] hashBytes = sha256.digest(inputBytes);
      
      // Convertir el arreglo de bytes del hash en una cadena hexadecimal
      StringBuilder sb = new StringBuilder();
      for (byte b : hashBytes) {
        sb.append(String.format("%02x", b));
      }
      sha256Hash = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    
    System.out.println("La cadena de entrada es: " + inputString);
    System.out.println("El hash SHA-256 de la cadena es: " + sha256Hash);
    return sha256Hash;
  }
  public boolean hashComparator(String expectedHash, String actualHash){
    if (expectedHash.equalsIgnoreCase(actualHash)) {
        System.out.println("Los hashes coinciden.");
        return true;
      } else {
        System.out.println("Los hashes no coinciden.");
        return false;
      }
  }

}


  
  
