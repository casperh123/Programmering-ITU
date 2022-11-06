package src;

public class SoftwareModul{

    //Felter
    private String codeBase = "En masse kode";
    private boolean opdateret;
    private int modulNummer;

    public SoftwareModul(boolean opdateret, int modulNummer){
        this.opdateret = opdateret;
        this.modulNummer = modulNummer;
    }

    public void opdaterModul(){
        if(opdateret) System.out.println("Modullet er allerede opdateret");

        else{
            System.out.println("Opdaterer modul...");
            opdateret=true;
            System.err.println("... Modul opdateret.");
        }
    }

    public boolean getOpdateret(){
        return opdateret;
    }
    public int getModulNummer(){
        return modulNummer;
    }
}