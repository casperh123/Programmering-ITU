import java.util.Scanner;

public class r2 {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int r1 = input.nextInt();
        int s = input.nextInt();
        int r2 = 2*s-r1;


        input.close();

        System.out.println(r2);

    }
}