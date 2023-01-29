import java.util.Scanner;

public class HeartRate {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for (int i = 0; i < testCases; i++) {

            int beats = input.nextInt();
            double time = input.nextDouble();
            double actualHeartBeats = beats / time * 60;

            double minRate = actualHeartBeats - (60 / time);
            double maxRate = actualHeartBeats + (60 / time);

            System.out.println(minRate);
            System.out.println(actualHeartBeats);
            System.out.println(maxRate);
        }
    }
}
