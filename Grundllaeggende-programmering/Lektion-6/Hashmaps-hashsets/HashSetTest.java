import java.util.*;


public class HashSetTest {
    
    static int unique(ArrayList<Integer> list) {
        
        HashSet<Integer> comparator = new HashSet<Integer>();
        
        for(int index : list) {
            comparator.add(index);
        }
        
        for (int element : comparator) {
            System.out.println(element);
        }
        
        return comparator.size();
    
    }
    
    static void hej() {
        
        Scanner input = new Scanner(System.in);
        
        int elementsToInput = input.nextInt();
        ArrayList<Integer> liste = new ArrayList<>();
        
        for(int i = 0; i < elementsToInput; i++) {
            liste.add(input.nextInt());
        }
        
        System.out.println("Array Fyldt");
        
        System.out.println("Unikke tal i listen: " + unique(liste));
    
    }
}