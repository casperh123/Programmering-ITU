import java.util.Scanner;

public class Sibice {
    public static void main(String args[]) {
      
        Scanner input = new Scanner(System.in);

        int matches = input.nextInt();
        int width = input.nextInt();
        int length = input.nextInt();
        Double area = Math.sqrt(Math.pow(width, 2) + Math.pow(length, 2));

        for (int i = 0; i < matches; i++) {

            int match = input.nextInt();

            System.out.println(match <= area ? "DA" : "NE");
        
        }
    }
}
