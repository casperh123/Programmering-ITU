import java.util.Scanner;

public class LigeUlige
{
    static void ligeUlige() {
        
        Scanner input = new Scanner(System.in);
        
        int nummer = input.nextInt();
        
        System.out.println(nummer % 2 == 0 ? "Lige" : "Ulige");
        input.close();
    }
    
    static void ligeUligeUendelig() {
        
        int i = 1;
        
        while(true) {
            System.out.println(i % 2 == 0 ? i + " er lige" : i + " er ulige");
            i++;
        }
    }
    
    
}
