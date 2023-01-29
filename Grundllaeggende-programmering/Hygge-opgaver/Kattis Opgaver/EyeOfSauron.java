import java.util.Scanner;

public class EyeOfSauron {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String[] inputString = input.nextLine().split("");
        int leftSide = 0;
        int rightSide = 0;
        boolean leftSideScanned = false;

        for(int i = 0; i < inputString.length; i++) {
            
            if(!leftSideScanned) {
                if (inputString[i].equals("|")) {
                    leftSide++;
                } else {
                    leftSideScanned = true;
                    i++;
                }
            } else {
                if (inputString[i].equals("|")) {
                    rightSide++;
                }
            }

        }

        System.out.println(leftSide == rightSide ? "correct" : "fix");
        
    }
}