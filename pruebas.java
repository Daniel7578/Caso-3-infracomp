public class pruebas {
    private HashGenerator hashGenerator = new HashGenerator();
    private String cadena = "arroz";
    private String hashdocumento;
    private String sal = "ci";

    public void correrPrueba(){
        
        this.hashdocumento = hashGenerator.hashingHexa(cadena, "SHA-256", sal);
        System.out.println(hashdocumento);
        
    }
}
