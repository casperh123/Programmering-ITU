import java.util.Scanner;

public class jumboJavelin {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int dataPoints = input.nextInt();
        int fuses = dataPoints - 1;
        int cumulativeLength = 0 - fuses;

        for (int i = 0; i < dataPoints; i++) {
            cumulativeLength += input.nextInt();
        }


        System.out.println(cumulativeLength);

    }
}
