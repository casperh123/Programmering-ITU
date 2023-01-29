import java.util.Scanner;

public class Pot {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int inputIterations = input.nextInt();
        int x = 0;
        

        for (int i = 0; i < inputIterations; i++) {

            int unsanitisedInteger = input.nextInt();
            int sanitisedInteger = unsanitisedInteger / 10;
            int exponent = unsanitisedInteger % 10;

            x += Math.pow(sanitisedInteger, exponent);

        }

        System.out.println(x);
    }
}