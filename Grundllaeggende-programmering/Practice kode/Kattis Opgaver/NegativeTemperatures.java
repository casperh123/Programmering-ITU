import java.util.Scanner;

public class NegativeTemperatures{
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int dataPoints = input.nextInt();
        int negativeTemperatures = 0;

        for (int i = 0; i < dataPoints; i++) {
            if (input.nextInt() < 0) {
                negativeTemperatures++;
            }
        }

        System.out.println(negativeTemperatures);

    }
}