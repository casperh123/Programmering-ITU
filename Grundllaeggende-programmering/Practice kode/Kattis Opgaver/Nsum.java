import java.util.Scanner;

public class Nsum {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();
        int sum = 0;

        for (int i = 0; i < testCases; i++) {
            sum += input.nextInt();
        }

        System.out.println(sum);
    }
}