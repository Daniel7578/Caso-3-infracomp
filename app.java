import java.util.*;
public class app {
    private int numeroThreads;
    private String tipoHash;
    private String sal;
    private String hashDocumento;
    private String cadena;
    private HashGenerator hashGenerator = new HashGenerator();
    List<Character> alphabetList = new ArrayList<Character>();
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
        for (char c = 'a'; c <= 'z'; c++) {
            alphabetList.add(c);
        }
        
        
    }
    
}