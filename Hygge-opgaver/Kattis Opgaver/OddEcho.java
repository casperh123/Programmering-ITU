import java.util.Scanner;

public class OddEcho {
    public static void main(String args[]) {

    Scanner input = new Scanner(System.in);

    int dataSet = input.nextInt();

    for(int i = 0; i <= dataSet; i++) {

        String possibleEcho = input.nextLine();

        if(!(i % 2 == 0)) {
            System.out.println(possibleEcho);
        }
    }
}
}
