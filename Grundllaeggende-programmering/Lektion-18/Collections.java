import java.util.*;

public class Collections
{
    public Collections() {
    }
    
    public void ArrayList() {
        
        List<Integer> numbers = new ArrayList<>();
        
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        
        for(int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
        
        for(int number : numbers) {
            System.out.println(number);
        }
        
    }
}
