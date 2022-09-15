import java.util.Scanner;
import java.util.HashSet;

public class DoublePassword {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String firstString = input.nextLine();
        String secondString = input.nextLine();
        String[] arrayFirstString = firstString.split("");
        String[] arraySecondString = secondString.split("");
        int differences = 0;
        int totalCombinations = 0;

        for(int i = 0; i < arrayFirstString.length; i++) {

            if(!(arrayFirstString[i].equals(arraySecondString[i]))) {
                differences++;
            }

        }

        if(differences == 0) {
            totalCombinations = 1;
        } else {
            totalCombinations = (int)Math.round(Math.pow(differences, differences));
        }

        System.out.println(totalCombinations);

    }
}
