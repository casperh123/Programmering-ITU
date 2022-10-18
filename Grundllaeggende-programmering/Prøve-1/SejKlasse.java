import java.util.*;

public class SejKlasse {
    
    //Opgave 1
    
    static boolean isReasonablePrice(int price) {
        if(price < 20) {
            return true;
        } else {
            return false;
        }
    }
    
    static void test0() {
        System.out.println(isReasonablePrice(15));
        System.out.println(isReasonablePrice(22));
    }
    
    static boolean isReasonablePrice(int price, int low, int high) {
        if(price >= low && price <= high) {
            return true;
        } else {
            return false;
        }
    }
    
    static void test1() {
        System.out.println(isReasonablePrice(8, 9, 25));
        System.out.println(isReasonablePrice(16, 9, 25));
        System.out.println(isReasonablePrice(32, 9, 25));
    }
    
    //Opgave 2
    
    static List<Integer> isReasonablePrice(List<Integer> prices, int low, int high) {
        
        List<Integer> reasonablePrices = new ArrayList<>();
        
        for(int price : prices) {
            if(isReasonablePrice(price, low, high)) {
                reasonablePrices.add(price);
            }
        }
        
        return reasonablePrices;
    }
    
    static void test2() {
        
        List<Integer> testPrices = new ArrayList<>();
        List<Integer> reasonablePrices = new ArrayList<>();
        
        testPrices.add(5);
        testPrices.add(17);
        testPrices.add(18);
        testPrices.add(28);
        
        reasonablePrices = isReasonablePrice(testPrices, 10, 25);
        
        System.out.println(reasonablePrices.size());
        
        for(int price : reasonablePrices) {
            System.out.println(price);
        }
    }
    
    //Opgave 3
    
    static Map<String, Integer> getMap() {
        
        Map<String, Integer> butterPrices = new HashMap<>();
        
        butterPrices.put("Lurpak", 15);
        butterPrices.put("Smørbar", 20);
        butterPrices.put("Kærgården", 25);
        
        return butterPrices;
    }
    
    static void test3() {
        
        Map<String, Integer> butterPrices = getMap();
        
        System.out.println(butterPrices.get("Lurpak"));
        System.out.println(butterPrices.get("Kærgården"));
    }
    
    // opgave 4
    
    static void printButterPrices(Map<String, Integer> prices, List<String> names) {
        
        System.out.println("Du kan vælge mellem dette smør:");
        
        for(String butter : names) {
            
            String butterPrice;
            
            if(prices.containsKey(butter)) {
                butterPrice = prices.get(butter).toString();
            } else {
                butterPrice = "??";
            }
            
            System.out.println("- " + butter + " (" + butterPrice + " kr)");
        }
        
    }
    
    static void test4() {
        
        List<String> products = new ArrayList<>();
        
        products.add("Lurpak");
        products.add("Smørbar");
        products.add("Kærgården");
        products.add("Egelykke");
        
        Map<String, Integer> butterPrices = getMap();
        
        printButterPrices(butterPrices, products);
    }
}