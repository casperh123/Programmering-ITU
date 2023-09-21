import java.util.Scanner;

public class DoublePassword {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] arrayFirstString = input.nextLine().split("");
        String[] arraySecondString = input.nextLine().split("");
        int differences = 0;
        int totalCombinations = 0;

        for(int i = 0; i < arrayFirstString.length; i++) {

            if(!(arrayFirstString[i].equals(arraySecondString[i]))) {
                differences++;
            }

        }

        totalCombinations = (int)Math.pow(2, differences);

        System.out.println(totalCombinations);

    }
}
