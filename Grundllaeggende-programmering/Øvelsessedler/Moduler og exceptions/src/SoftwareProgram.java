package src;

import java.util.ArrayList;
import java.util.List;

public class SoftwareProgram {

    private String navn;
    private List<SoftwareModul> moduler = new ArrayList<>();

    public SoftwareProgram(String navn){
        this.navn=navn;
    }

    public void tilfoejModul(SoftwareModul modul){
        moduler.add(modul);
    }

      public void koerProgram(){
        
        int modulerKoert=0;
        
        for(SoftwareModul modul : moduler){
            try {
                if(!modul.getOpdateret()) throw new ModulException(modul);
                modulerKoert++;
            } catch (ModulException e) {
                modul.opdaterModul();
                modulerKoert++;
            }
        }
        
        if(modulerKoert == moduler.size()){System.out.println("Program kørt.");}
    }

}
