import java.util.Scanner;

public class ShatteredCake {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int width = input.nextInt();
        int cakePieces = input.nextInt();
        int area = 0;

        for (int i = 0; i < cakePieces; i++) {
            area += input.nextInt() * input.nextInt();
        }

        System.out.println(area / width);
    }
}