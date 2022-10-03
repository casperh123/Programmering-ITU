import java.util.Scanner;

public class SMIL {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String inputString = input.next();

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == ':' || inputString.charAt(i) == ';') {
                if (inputString.length() - i > 2) {
                    if (inputString.charAt(i + 1) == '-') {
                        if (inputString.charAt(i + 2) == ')') {
                            System.out.println(i);
                        }
                    }

                }
               
                if (inputString.length() - i > 1 && inputString.charAt(i + 1) == ')') {
                    System.out.println(i);
                }
            }
        }
    }
}
