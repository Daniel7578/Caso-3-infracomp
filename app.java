import java.math.BigInteger;
import java.util.*;
public class app {
    private int numeroThreads;
    private String tipoHash;
    private String sal;
    private byte[] hashDocumento;
    private String cadena;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String alfabetoIncomletoT1 = "abcdefghijklm";
    String alfabetoIncomletoT2 = "nopqrstuvwxyz";
    private HashGenerator hashGenerator = new HashGenerator();
    private int tamanioContrasenia = 7;
    private Decifrador decifrador;
    public void metodoPrincipal () throws Exception{
        try (Scanner lectorInputs = new Scanner(System.in)) {
            System.out.println("Ingrese el tipo de algoritmo que desea usar: ");
            System.out.println("1 - SHA-256");
            System.out.println("2 - SHA-512");
            String inputHash = lectorInputs.nextLine();
            System.out.println(inputHash);
            
            if (inputHash.equals("1")){
                this.tipoHash = "SHA-256";
            }
            else if (inputHash.equals("2")){
                this.tipoHash = "SHA-512";
            }
            else{
                throw new Exception("Digite un numero valido");
            }
            System.out.println("Ingrese el hash a encontrar:");
            this.cadena = lectorInputs.nextLine();
            System.out.println("Ingrese la sal: ");
            this.sal = lectorInputs.nextLine();
            System.out.println("Ingrese el numero de thread que desea usar: ");
            System.out.println("1 - Thread");
            System.out.println("2 - Threads");
            String inputThread = lectorInputs.nextLine();
            if (inputThread.equals("1")){
                this.numeroThreads = 1;
            }
            else if (inputThread.equals("2")){
                this.numeroThreads = 2;
            }
            else{
                throw new Exception("Digite un numero valido");
            }
        }
        byte[] cadena2 = this.hashGenerator.hashing("ajedrez", tipoHash, sal);
        BigInteger bigInt = new BigInteger(cadena,16);
        this.hashDocumento = bigInt.toByteArray();
        if (hashDocumento.length > 64) {
            hashDocumento = Arrays.copyOfRange(hashDocumento, hashDocumento.length - 64, hashDocumento.length);
        }
        else if (hashDocumento[0] == 0) {
            hashDocumento = Arrays.copyOfRange(hashDocumento, 1, hashDocumento.length);
        }
        System.out.println(cadena2.length);
        System.out.println(hashDocumento.length);
        System.out.println(hashGenerator.hashComparator(cadena2, hashDocumento));
        char [] alphabetList = alphabet.toCharArray();
        if (numeroThreads == 1){
            this.decifrador = new Decifrador(hashDocumento, sal, tipoHash);
            long startTime = System.nanoTime();
            for (int tmpLenPassword  = 1; tmpLenPassword <= tamanioContrasenia; tmpLenPassword ++){
                decifrador.constructorCombinaciones(alphabetList, new StringBuilder(), alphabetList.length, tmpLenPassword);
            }
            long endTime = System.nanoTime();
            double tiempo = (endTime - startTime)/ 1000000000.0;
            System.out.println("La contrasenia encontrada fue: "+decifrador.getContraseniaFinal());
            System.out.println("El programa tardo: "+ tiempo);

        }
        else{
            char [] alphabetIncompletet1 = alfabetoIncomletoT1.toCharArray();
            char [] alphabetIncompletet2 = alfabetoIncomletoT2.toCharArray();
            CombinatorioaRunnable t1 = new CombinatorioaRunnable(1, alphabetIncompletet1, new StringBuilder(), alphabetIncompletet1.length, tamanioContrasenia, tipoHash, sal, hashDocumento, alphabetList);
            CombinatorioaRunnable t2 = new CombinatorioaRunnable(2, alphabetIncompletet2, new StringBuilder(), alphabetIncompletet2.length, tamanioContrasenia, tipoHash, sal, hashDocumento, alphabetList);
            long startTime = System.nanoTime();
            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.nanoTime();
            if (t1.getEncontrada()){
                System.out.println("La contrasenia encontrada fue: " + t1.getContraseniaFinal());

            }
            else if (t2.getEncontrada()){
                System.out.println("La contrasenia encontrada fue: " + t2.getContraseniaFinal());
            }
            double tiempo = (endTime - startTime)/ 1000000000.0;
            System.out.println("El programa tardo: "+ tiempo);

        }
    }
    
}