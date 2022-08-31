import java.util.Scanner;

public class qualityAdjustedLifeYear {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int qualityPeriods = input.nextInt();
        double accumulatedQuality = 0;

        for (int i = 0; i < qualityPeriods; i++) {
            accumulatedQuality += input.nextDouble() * input.nextDouble();
        }

        System.out.println(accumulatedQuality);

    }
}
