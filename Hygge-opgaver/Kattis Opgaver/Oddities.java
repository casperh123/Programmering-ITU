import java.util.Scanner;

public class Oddities {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();
        
        for(int i = 0; i < testCases; i++) {
            int inputTal = input.nextInt();
            System.out.println(inputTal % 2 == 0 ? inputTal + " is even" : inputTal + " is odd");
        }

    }
}
