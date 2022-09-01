
import java.util.*;
public class sidstPaaMaaneden
{
    static void sidstMaaneden() {
        
        Scanner input = new Scanner(System.in);
        
        int dayOfMonth = input.nextInt();
        
        if (dayOfMonth >= 25) {
            System.out.println("Wuhuu det er sidst på måneden");
        } else {
            System.out.println("Noo det er ikke sidst på måneden");
        }
    
    }
}
