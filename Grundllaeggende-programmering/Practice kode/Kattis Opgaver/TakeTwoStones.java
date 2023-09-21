import java.util.Scanner;

public class TakeTwoStones {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int stones = input.nextInt();

        System.out.println(stones % 2 == 0 ? "Bob" : "Alice");
    }
}
