import java.util.ArrayList;
import java.util.List;

public class Exeptions
{
    static void nullPointerException() {
        
        String hej = null;
        
        hej.toLowerCase();
    
    }
    
    static void outOfBounds() {
        
        int[] hej = new int[0];
        
        System.out.println(hej[1]);
    }
    
    static void arithmetic() {
        
        int hej = 5/0;
        
    }
}
