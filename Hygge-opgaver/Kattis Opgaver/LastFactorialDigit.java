import java.util.Scanner;

public class LastFactorialDigit {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {

            int uncalculatedFactorial = input.nextInt();
            int calculatedFactorial = uncalculatedFactorial;

            for (int index = (uncalculatedFactorial - 1); index > 1; index--) {
                calculatedFactorial *= index;
            }
    
            String[] factorialArray = Integer.toString(calculatedFactorial).split("");
    
            System.out.println(factorialArray[factorialArray.length - 1]);

        }
    }
}
