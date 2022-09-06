import java.util.Scanner;
import java.util.ArrayList;


public class ReversedBinaryNumbers {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int numberToConvert = input.nextInt();
        int convertetNumber = 0;
        String[] binaryArray = [""];

        while(numberToConvert > 0) {
            binaryArray[] = String.valueOf(numberToConvert % 2);
            numberToConvert /= 2;
        }
        
        for(int number : binaryNumber) {
            System.out.print(number);
        }

    }
}
