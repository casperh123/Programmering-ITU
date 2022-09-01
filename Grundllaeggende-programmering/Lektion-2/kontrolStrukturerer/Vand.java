import java.util.Scanner;

public class Vand {
    static void vand() {
        
        Scanner input = new Scanner(System.in);
        
        double weight = input.nextDouble();
        double waterToDrink = weight * 35.0;
        double extraModifier = input.nextDouble();
        
        System.out.println(waterToDrink * extraModifier);
        
    }
}