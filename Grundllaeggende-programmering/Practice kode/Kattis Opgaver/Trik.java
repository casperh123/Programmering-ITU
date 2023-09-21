import java.util.Scanner;

public class Trik {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String[] inputString = input.nextLine().split("");

        int ballIndex = 1;

        for (int i = 0; i < inputString.length; i++) {
            if(inputString[i].equals("A")) {
                switch(ballIndex) {
                    case 1:
                        ballIndex++;
                        break;
                    case 2:
                        ballIndex--;
                        break;
                }
            } else if(inputString[i].equals("B")) {
                switch(ballIndex) {
                    case 2:
                        ballIndex++;
                        break;
                    case 3:
                        ballIndex--;
                        break;
                }   
            } else {
                switch(ballIndex) {
                    case 1:
                        ballIndex = 3;
                        break;
                    case 3:
                        ballIndex = 1;
                        break;
                }
            }
        }

        System.out.println(ballIndex);

    }
}
