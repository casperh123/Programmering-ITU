import java.util.ArrayList;
import java.util.Scanner;

public class heltTalArray
{
        static void genererListe() {
        
            ArrayList<Integer> sejListe = new ArrayList<>();
    
            for(int i = 1; i <= 10; i++) {
                sejListe.add(i);
            }   
        
            System.out.println(sejListe.get(5));
            
        }
        
        static int minimum(ArrayList<Integer> liste) {
            
            int mindsteTal = 0;
            
            for (int tal : liste) {
                
                if(mindsteTal == 0) {
                    mindsteTal = tal;
                } else if(tal < mindsteTal) {
                    mindsteTal = tal;
                }
                
            }
            
            
            return mindsteTal;
            
        }
        
        static int gennemsnit(ArrayList<Integer> liste){
            
            int sum = 0;
            
            for (int tal : liste) {
                sum += tal;
            }
            
            return sum / liste.size();
        }
        
        static void outPutMinimumGennemSnit() {
            
            Scanner input = new Scanner(System.in);
            
            ArrayList<Integer> yeahMan = new ArrayList<>();
            
            for (int i = 0; i < 10; i++) {
                yeahMan.add(input.nextInt());
            }
            
            System.out.println(minimum(yeahMan));
            System.out.println(gennemsnit(yeahMan));
            
        }
}
