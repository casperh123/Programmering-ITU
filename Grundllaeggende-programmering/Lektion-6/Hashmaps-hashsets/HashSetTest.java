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
    
    static void uniqueTester() {
        
        Scanner input = new Scanner(System.in);
        
        int elementsToInput = input.nextInt();
        ArrayList<Integer> liste = new ArrayList<>();
        
        for(int i = 0; i < elementsToInput; i++) {
            liste.add(input.nextInt());
        }
        
        System.out.println("Array Fyldt");
        
        System.out.println("Unikke tal i listen: " + unique(liste));
    
    }
    
    public static int uniqueNames(ArrayList<String> nameList) {
        
        HashSet<String> uniqueNames = new HashSet<>();
        
        for(String name : nameList) {
            uniqueNames.add(name);
        }
        
        return uniqueNames.size();
    }
    
    public static void uniqueNamesTester() {
        
        Scanner input = new Scanner(System.in);
        int numberInputs = input.nextInt();
        ArrayList<String> names = new ArrayList<>();
        
        for (int i = 0; i < numberInputs; i++) {
            names.add(input.next());
        }
        
        int unikkeNavne = uniqueNames(names);
        
        System.out.println(unikkeNavne + " unikke navne.");
        
    }
    
    public static int uniqueNameslengths(ArrayList<String> namesList) {
        
        HashSet<Integer> nameLengths = new HashSet<>();
        
        for (String name : namesList) {
            
            nameLengths.add(name.length());
        
        }
        
        return nameLengths.size();
        
    }
    
    public static void uniqueNamesLengthsTester() {
        
        Scanner input = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<>();
        
        int testCases = input.nextInt();
        
        for(int i = 0; i < testCases; i++) {
            nameList.add(input.next());
        }
        
        System.out.println(uniqueNameslengths(nameList));
        
    }
    
    public static void nameLengths(ArrayList<String> nameList) {
        
        HashMap<Integer, Integer> nameLengthOccurence = new HashMap<>();
        
        for(String name : nameList) {
            
            int nameLength = name.length();
            int indexNumber = 0;
            
            if(nameLengthOccurence.containsKey(nameLength)) {
                
                indexNumber = nameLengthOccurence.get(nameLength);
                
            }
            
            nameLengthOccurence.put(nameLength, indexNumber + 1);
            
        }
        
        System.out.println(nameLengthOccurence.keySet());
        System.out.println(nameLengthOccurence.values());
    }
    
    public static void nameLengthOccurenceTester() {
        
        Scanner input = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<>();
        
        int testCases = input.nextInt();
        
        for(int i = 0; i < testCases; i++) {
            nameList.add(input.next());
        }
        
        nameLengths(nameList);
        
    }
}