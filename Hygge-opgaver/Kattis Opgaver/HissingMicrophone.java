import java.util.Scanner;

public class HissingMicrophone {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String word = input.next();
        
        for (int i = 0; i < word.length(); i++) {

            if (i == word.length() - 1) {
                break;
            } else if (word.substring(i, i + 2).equals("ss")) {
                System.out.println("hiss");
                return;
            }

        }

        System.out.println("no hiss");

    }
}
