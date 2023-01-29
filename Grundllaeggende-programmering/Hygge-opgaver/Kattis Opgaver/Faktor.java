import java.util.Scanner;

public class Faktor {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int articlesToPublish = input.nextInt();
        int targetImpactFactor = input.nextInt();
    
        System.out.println(articlesToPublish * (targetImpactFactor - 1) + 1);
    }
}
