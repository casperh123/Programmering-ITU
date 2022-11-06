package src;

public class ModulException extends Exception {
    
    private SoftwareModul modul;

    public ModulException(SoftwareModul modul){
        this.modul = modul;
    }

    public int faaFejlModulNummer(){
        return modul.getModulNummer();
    }

    public SoftwareModul faaFejlModul(){
        return modul;
    }
}
