import java.util.Scanner;

public class FizzBuzz {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int firstDivisor = input.nextInt();
        int secondDivisor = input.nextInt();
        int iterations = input.nextInt();
        

        for (int i = 1; i <= iterations; i++) {
            
            String output = "";
            
            if (i % firstDivisor == 0) {
                output += "Fizz";
            } 
            if (i % secondDivisor == 0) {
                output += "Buzz";
            } 
            if (i % firstDivisor != 0 && i % secondDivisor != 0) {
                System.out.println(i);
            } else {
                System.out.println(output);
            }
        }
    }
}
