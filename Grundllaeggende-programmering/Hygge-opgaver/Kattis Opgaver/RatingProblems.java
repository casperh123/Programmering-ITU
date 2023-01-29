import java.util.Scanner;

public class RatingProblems {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int judges = input.nextInt();
        int alreadyRated = input.nextInt();
        double theoryMin = 0;
        double theoryMax = 0;

        for (int i = 0; i < judges; i++ ) {
            if(i < alreadyRated) {
                int rating = input.nextInt();
                theoryMin += rating;
                theoryMax += rating;
            } else {
                theoryMin += -3;
                theoryMax += 3;
            }
        }

        theoryMin /= judges;
        theoryMax /= judges;

        System.out.println(theoryMin + " " + theoryMax);
    }
}