import java.util.Scanner;

public class DigitSwap{
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int intToSwap = input.nextInt();
        int firstDigit = intToSwap % 10;
        int secondDigit = intToSwap / 10;

        input.close();

        System.out.println(firstDigit + "" + secondDigit);



    }
}