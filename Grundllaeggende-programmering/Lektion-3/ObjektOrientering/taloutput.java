import java.util.Scanner;

public class taloutput {
    static void output() {

        Scanner input = new Scanner(System.in);

        int tal = input.nextInt();    

        while(tal != 1) {
            if(tal % 2 == 0) {
                tal = tal / 2;
            } else {
                tal = tal * 3 +1;
            }
             System.out.println(tal);
        }
    }
}