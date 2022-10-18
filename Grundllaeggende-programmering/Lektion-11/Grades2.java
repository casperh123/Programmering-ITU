import java.io.File;
import java.util.*;

public class Grades2{

    static void WhoGotWhichGrade() throws Exception {
        
        File Mannes = new File("./karakterer2.txt");
        Scanner input = new Scanner(Mannes); 
        
        Map<String, List<String>> karakterer = new HashMap<>();
        
        while(input.hasNextLine()) {
            
            String[] line = input.nextLine().toLowerCase().split(" ");
            
            String name = line[0];
            String karakter = line[1];
            
            if(karakterer.containsKey(karakter)) {
                List<String> names = karakterer.get(karakter);
                names.add(name);
            } else {
                List<String> nameList = new ArrayList<>();
                nameList.add(name);
                karakterer.put(karakter, nameList);
            }
            
        }
        
        for(String karakter : karakterer.keySet()) {
            
            System.out.print(karakter + ": ");
            
            for(String name : karakterer.get(karakter)) {
                System.out.print(name + " ");
            }
            
            System.out.println();
        }
    }

    
}