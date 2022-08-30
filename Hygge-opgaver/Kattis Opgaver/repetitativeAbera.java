import java.util.Scanner;

public class repetitativeAbera {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int repetitions = input.nextInt();

        for (int i = 1; i <= repetitions; i++)  {
            System.out.println(i + " Abracadabra");
        }

    }
}