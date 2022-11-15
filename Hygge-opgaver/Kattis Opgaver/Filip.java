import java.util.Scanner;

public class Filip {

    public static int reverse(int numberToReverse) {
        
        int reversedNumber = 0;
        int remainder = 0;
        int number = numberToReverse;
        int numberOfDigits = 0;
        int numbertoDigit = number;

        while (numbertoDigit != 0) {
            numbertoDigit /= 10;
            numberOfDigits++;
        }

        for (int i = numberOfDigits; i > 0; i--) {            
            remainder = number % 10;
            reversedNumber = reversedNumber * 10 + remainder;
            number /= 10;
        }

        return reversedNumber;

    }
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int firstNumber = input.nextInt();
        int secondNumber = input.nextInt();
        int firstNumberReversed = reverse(firstNumber);
        int secondNumberReversed = reverse(secondNumber);

        System.out.println(firstNumberReversed > secondNumberReversed 
                            ? firstNumberReversed : secondNumberReversed);

                            
    }
}
