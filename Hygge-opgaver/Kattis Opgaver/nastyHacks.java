import java.util.Scanner;

public class NastyHacks {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for (int i = 0; i < testCases; i++ ) {

            int noAdvertisementRevenue = input.nextInt();
            int advertisementRevenue = input.nextInt();
            int advertisementCost = input.nextInt();

            if (advertisementRevenue - advertisementCost > noAdvertisementRevenue) {
                System.out.println("advertise");
            } else if (advertisementRevenue - advertisementCost == noAdvertisementRevenue) {
                System.out.println("does not matter");
            } else {
                System.out.println("do not advertise");
            }

        }

    }
}
