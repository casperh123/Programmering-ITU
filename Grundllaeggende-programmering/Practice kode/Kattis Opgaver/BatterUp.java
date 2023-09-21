import java.util.Scanner;

public class BatterUp {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int atBases = input.nextInt();
        double baseScores = 0;
        float walks = 0;

        for (int i = 0; i < atBases; i++) {
            
            int base = input.nextInt();

            if(base < 0) {
                walks++;
            } else {
                baseScores += base;
            }
        }

        System.out.println(baseScores / (atBases - walks));
    }
}
