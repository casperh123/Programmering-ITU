import java.util.Scanner;

public class repetitativeEcho{
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String output = input.nextLine();

        input.close();

        for (int i = 0; i < 3 ; i++) {
            System.out.print(output + " "); 
        }

    }
}