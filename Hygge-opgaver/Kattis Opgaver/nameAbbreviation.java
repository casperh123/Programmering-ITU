import java.util.Scanner;

public class NameAbbreviation {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        String names = input.nextLine();
        String abbreviatedNames = "";
        String[] indexedNames = names.split("-");

        input.close();
        
        for (int i = 0; i < indexedNames.length; i++) {
            abbreviatedNames += indexedNames[i].charAt(0);
        }

        System.out.println(abbreviatedNames);
        
    }
}