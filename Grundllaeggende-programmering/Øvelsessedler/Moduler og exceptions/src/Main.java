package src;

public class Main {
    public static void main(String[] args) throws ModulException {
        //Opret program
        SoftwareProgram program = new SoftwareProgram("RedJ");

        //Fyld program med moduller
        for(int i=1;i<246;i= (int) (i*1.1+3)){
            boolean opdateret = true;
            if(i%4==0) opdateret=false;
            program.tilfoejModul(new SoftwareModul(opdateret, i));
        }

       program.koerProgram();
        
    }
}
