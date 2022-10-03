import java.util.*;

public class ProblemAFlest {

    public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);

    String[] drinksToCompare = input.nextLine().split(" ");
    
    Map<String, Integer> drinksCount = new TreeMap<>();

    int testCases = Integer.parseInt(input.nextLine());

    for(int i = 0; i < testCases; i++) {

        Map<String, Integer> occurences = new HashMap<>();

        String note = input.nextLine();
        String[] noteArray = note.toLowerCase().split("[,.!\\s]");

        for(String word : noteArray) {

            for(String drink : drinksToCompare) {

                if(word.equals(drink)) {
                    occurences.merge(drink, 1, (a, b) -> a + b);
                    break;
                }
            }
        }

        String maxOccurences = null;
        
        for (String drink : occurences.keySet()) {
            if(maxOccurences == null || occurences.get(drink) > occurences.get(maxOccurences)) {
                maxOccurences = drink;
            }
        }

        if(maxOccurences != null) {
            drinksCount.merge(maxOccurences, 1, (a, b) -> a + b);
        }
    } 

    int mostOccurences = 0;

    for(String drink : drinksCount.keySet()) {
        if(drinksCount.get(drink) > mostOccurences) {
            mostOccurences = drinksCount.get(drink);
        }
    }
    
    for(String drink : drinksCount.keySet()) {
        if(drinksCount.get(drink) == mostOccurences) {
            System.out.println(drink);
        }
    }
}
}