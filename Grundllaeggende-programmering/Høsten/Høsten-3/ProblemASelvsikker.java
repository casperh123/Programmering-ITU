import java.util.Scanner;



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

        sortedString += capitalize(unsortedArray[1]) + " ";
        sortedString += unsortedArray[0].replaceAll("([A-Z])", "$1").toLowerCase() + " ";

        for (int i = 2; i < unsortedArray.length; i++) {

            if (i == (unsortedArray.length - 1)) {
                sortedString += unsortedArray[i];
            } else {
                sortedString += unsortedArray[i] + " ";
            }

        }

        sortedString = sortedString.replace("?", "!");

        System.out.println(sortedString);
    }
}
