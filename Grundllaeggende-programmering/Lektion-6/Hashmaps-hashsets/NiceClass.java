import java.util.Scanner;
import java.util.*;

public class NiceClass {
    
    static boolean contains(ArrayList<String> list, String comparator) {
 
        for(String element : list) {
            if(element.equals(comparator)) {
                return true;
            }
        }
        
        return false;
    }
    
    static void test() {
        
        Scanner input = new Scanner(System.in);
        
        int wantedInputs = input.nextInt();
        ArrayList<String> mannes = new ArrayList<>();
        
        for (int i = 0; i < wantedInputs; i++) {
            mannes.add(input.next());
        }
        
        System.out.println("Hell yeah, listen er fuld");

        boolean inputValid = true;
        
        while(inputValid) {
        
        String comparator = input.next();
        inputValid = contains(mannes, comparator);        
        System.out.println(comparator + " er i listen: " + inputValid); 
        
        }
    }
}