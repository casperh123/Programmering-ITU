import java.util.Scanner;
import java.util.ArrayList;

public class Pet {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> sumOfScores = new ArrayList<Integer>();
        int maxScore = 0;
        int winningContestant = 0;

        for(int contestant = 0; contestant < 5; contestant++) {

            int sumOfScore = 0;

            for(int i = 0; i < 4; i++) {
                sumOfScore += input.nextInt();
            }

            sumOfScores.add(sumOfScore);
        }

        for(int i = 0; i < sumOfScores.size(); i++) {
            if(sumOfScores.get(i) > maxScore) {
                maxScore = sumOfScores.get(i);
                winningContestant = i + 1;
            }
        }

        System.out.println(winningContestant + " " + maxScore);

    }
}
