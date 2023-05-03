import java.util.*;
public class app {
    private int numeroThreads;
    private String tipoHash;
    private String sal;
    private byte[] hashDocumento;
    private String cadena;
    private HashGenerator hashGenerator = new HashGenerator();
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int tamanioContrasenia = 7;
    private Decifrador decifrador;
    public void metodoPrincipal (){
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
                System.out.println("Digite un numero valido");
            }
            System.out.println("Ingrese la cadena para hacer hash");
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
                System.out.println("Digite un numero valido");
            }
        }
        this.hashDocumento = hashGenerator.hashing(cadena,tipoHash,sal);
        char [] alphabetList = alphabet.toCharArray();
        //this.decifrador = new Decifrador(hashDocumento, sal, tipoHash);
        long startTime = System.nanoTime();
        //for (int tmpLenPassword  = 1; tmpLenPassword <= tamanioContrasenia; tmpLenPassword ++){
        //    decifrador.constructorCombinaciones(alphabetList, new StringBuilder(), alphabetList.length, tmpLenPassword);
        //}
        String alfabetoIncomletoT1 = "abcdefghijklm";
        String alfabetoIncomletoT2 = "nopqrstuvwxyz";

        char [] alphabetIncompletet1 = alfabetoIncomletoT1.toCharArray();
        char [] alphabetIncompletet2 = alfabetoIncomletoT2.toCharArray();
        CombinatorioaRunnable t1 = new CombinatorioaRunnable(1, alphabetIncompletet1, new StringBuilder(), alphabetIncompletet1.length, tamanioContrasenia, tipoHash, sal, hashDocumento, alphabetList);
        CombinatorioaRunnable t2 = new CombinatorioaRunnable(2, alphabetIncompletet2, new StringBuilder(), alphabetIncompletet2.length, tamanioContrasenia, tipoHash, sal, hashDocumento, alphabetList);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.nanoTime();
        double tiempo = (endTime - startTime)/ 1000000000.0;
        System.out.println(t1.getContraseniaFinal());
        System.out.println(t2.getContraseniaFinal());
        //System.out.println(decifrador.getContraseniaFinal());
        System.out.println(tiempo);        
    }
    
}