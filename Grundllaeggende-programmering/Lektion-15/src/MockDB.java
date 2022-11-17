package src;
import java.util.HashMap;
 
public class MockDB {
    private HashMap<Integer, int[]> customerData = new HashMap<>() {{
        put(42, new int[] {45,44,39,39,39,44,40});
        put(43, new int[] {24,27,31,23,28,23,34});
        put(44, new int[] {24,23,28,31,25,26,21});
        put(45, new int[] {27,22,31,32,35,20,27});
        put(46, new int[] {23,40,27,34,21,38,42});
        put(47, new int[] {41,22,38,21,25,28,35});
    }};
 
    public HashMap<Integer, int[]> getCustomerData() {
        return customerData;
    }
}