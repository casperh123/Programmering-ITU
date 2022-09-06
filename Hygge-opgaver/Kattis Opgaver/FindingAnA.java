import java.util.Scanner;

public class FindingAnA {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String unsanitisedInput = input.nextLine();
        String[] charArray = unsanitisedInput.split("");
        boolean firstAFound = false;


        for (String indexedChar : charArray) {

            if (indexedChar.equals("a") && !firstAFound) {
                
                firstAFound = true;

            } 
            if (firstAFound) {
                System.out.print(indexedChar);
            } 

        }

    }
}
