import java.util.Scanner;

public class GCVWR {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int vehicleGrossCombinedWeight = input.nextInt();
        int vehicleWeight = input.nextInt();
        int itemsToBring = input.nextInt();
        int totalItemWeight = 0;

        for (int i = 0; i < itemsToBring; i++) {
            totalItemWeight += input.nextInt();
        }

        int towWeight = (vehicleGrossCombinedWeight - vehicleWeight) * 90 / 100;

        System.out.println(towWeight - totalItemWeight);
        
    }
}