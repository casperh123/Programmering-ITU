import java.util.Scanner;

public class ElectricalOutlets {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for (int i = 0; i < testCases; i++) {

            int poweredAppliances = 1;
            int powerStrips = input.nextInt();

            for (int indexedStrip = 0; indexedStrip < powerStrips; indexedStrip++) {
                poweredAppliances += input.nextInt() - 1;
            }

            System.out.println(poweredAppliances);

        }

    }
}
