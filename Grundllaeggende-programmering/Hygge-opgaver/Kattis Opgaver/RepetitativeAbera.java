import java.util.Scanner;

public class RepetitativeAbera {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int repetitions = input.nextInt();

        input.close();

        for (int i = 1; i <= repetitions; i++)  {
            System.out.println(i + " Abracadabra");
        }

    }
}