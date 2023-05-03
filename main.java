import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
		try(Scanner lectorInputs = new Scanner(System.in)){
			System.out.println("Seleccione una opcion");
			System.out.println("1 - Correr programa");
			System.out.println("2 - Constructor de hashes en hexa");
			String inpuString = lectorInputs.nextLine();
			if (inpuString.equals("1")){
				app app = new app();
				app.metodoPrincipal();
			}
			else{
				pruebas pruebas = new pruebas();
				pruebas.correrPrueba();
			}


		}
	}
    
}
