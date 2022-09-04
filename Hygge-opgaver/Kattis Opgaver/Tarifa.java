import java.util.Scanner;

public class Tarifa {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int megatbytesPerMonth = input.nextInt();
        int months = input.nextInt();
        int totalBandwidth = 0;

        for (int i = 0; i < months; i++) {
            totalBandwidth += megatbytesPerMonth - input.nextInt();
        }

        System.out.println(totalBandwidth + megatbytesPerMonth);
    }
}