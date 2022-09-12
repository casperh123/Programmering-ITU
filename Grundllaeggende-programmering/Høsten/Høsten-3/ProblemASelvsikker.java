import java.util.Scanner;
import java.util.ArrayList;



public class ProblemASelvsikker {

    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
    
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] unsortedArray = input.nextLine().split(" ");
        String sortedString = "";

        for (int i = 0; i < unsortedArray.length; i++) {

            switch (i) {
                case 0:
                    sortedString += capitalize(unsortedArray[i + 1]) + " ";
                    break;
                case 1:
                    sortedString += unsortedArray[i - 1].replaceAll("([A-Z])", "$1").toLowerCase() + " ";
                    break;
                default:
                    sortedString += unsortedArray[i] + " ";
                    break;
            }

            sortedString = sortedString.replace("?", "!");
        }

        System.out.println(sortedString);
    }
}
