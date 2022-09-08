import java.util.Scanner;

public class GrassSeedInc {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        double sowCost = input.nextDouble();
        int lawnsToSow = input.nextInt();
        double totalSowCost = 0;

        for (int i = 0; i < lawnsToSow; i++) {

            double lawnWidth = input.nextDouble();
            double lawnLength = input.nextDouble();

            totalSowCost += lawnLength * lawnWidth * sowCost;
        }

        System.out.println(totalSowCost);

    }
}