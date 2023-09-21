import java.util.Scanner;


public class ReversedBinaryNumbers {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        Integer numberToConvert = input.nextInt();
        String numberBinaryString = Integer.toBinaryString(numberToConvert);
        String numberReversedBinaryString = "";


        for(int i = (numberBinaryString.length() - 1); i >= 0; i--) {
            numberReversedBinaryString += numberBinaryString.charAt(i);
        }

        System.out.println(Integer.parseInt(numberReversedBinaryString, 2));

    }
}
