import java.util.Scanner;
import java.util.ArrayList;

public class DiceCup {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int firstDiceFaces = input.nextInt();
        int secondDiceFaces = input.nextInt();
        int highestSum = firstDiceFaces + secondDiceFaces;
        int mostLikelySum = 0;
        int[] outcomeCount = new int[highestSum - 1];
        int[] combinationSum = new int[highestSum - 1];

        for (int i = 2; i <= highestSum; i++) {
            combinationSum[i - 2] = i;
        }

        for (int firstDice = 1; firstDice <= firstDiceFaces; firstDice++) {

            for (int secondDice = 1; secondDice <= secondDiceFaces; secondDice++) {

                int outcome = firstDice + secondDice;
                
                outcomeCount[outcome - 2]++;

                if(outcomeCount[outcome - 2] > mostLikelySum) {
                    mostLikelySum = outcomeCount[outcome - 2];
                }
            }
        }

        for (int i = 0; i < outcomeCount.length; i++) {
            if (outcomeCount[i] == mostLikelySum) {
                System.out.println(i + 2);
            }
        }

        
        
    }
}
