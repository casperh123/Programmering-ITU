import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class GinFizz {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        int gin = 45 * amount;
        int lemonJ = 30 * amount;
        int syrup = 10 * amount;
        int lemon = 1 * amount;

        System.out.println(gin + " ml gin");
        System.out.println(lemonJ + " ml fresh lemon juice");
        System.out.println(syrup + " ml simple syrup");
        if(lemon != 1) {
            System.out.println(lemon + " slices of lemon");
        } else {
            System.out.println(lemon + " slice of lemon");

        }
        input.close();
    }
}
