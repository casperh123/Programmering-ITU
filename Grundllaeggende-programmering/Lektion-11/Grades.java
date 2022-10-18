import java.io.File;
import java.util.*;

public class Grades{

    static void average() throws Exception {
        
        File Mannes = new File("./karakterer2.txt");
        Scanner input = new Scanner(Mannes); 
        
        List<Integer> grades = new ArrayList<>();
        
        int sum = 0;
        
        while(input.hasNextLine()) {
            sum += Integer.parseInt(input.nextLine());
        }
        
        System.out.println(sum / grades.size());
    }

    
}