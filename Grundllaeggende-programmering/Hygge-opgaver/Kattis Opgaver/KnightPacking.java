import java.util.Scanner;

public class KnightPacking {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int rounds = input.nextInt();

        if(rounds % 2 == 0) {
            System.out.println("second");
        } else {
            System.out.println("first");
        }


    }
}
