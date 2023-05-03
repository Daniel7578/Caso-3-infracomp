import java.util.Scanner;

public class pruebas {
    private HashGenerator hashGenerator = new HashGenerator();
    private String cadena;
    private String hashdocumento;
    private String sal;
    private String tipoHash;

    public void correrPrueba(){
        try(Scanner lectorInputs = new Scanner(System.in)){
			System.out.println("Escriba una cadena: ");
			cadena = lectorInputs.nextLine();
			System.out.println("Escriba una sal: ");
            sal = lectorInputs.nextLine();
            System.out.println("Ingrese el tipo de algoritmo que desea usar: ");
            System.out.println("1 - SHA-256");
            System.out.println("2 - SHA-512");
            String inputHash = lectorInputs.nextLine();
            System.out.println(inputHash);
            System.out.println(cadena+sal);
            
            if (inputHash.equals("1")){
                this.tipoHash = "SHA-256";
            }
            else if (inputHash.equals("2")){
                this.tipoHash = "SHA-512";
            }
            this.hashdocumento = hashGenerator.hashingHexa(cadena,tipoHash, sal);
            System.out.println(hashdocumento);
        }
        
    }
}
