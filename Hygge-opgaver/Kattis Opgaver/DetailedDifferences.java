import java.util.Scanner;

public class DetailedDifferences {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {

            String firstSequence = input.next();
            String secondSequence = input.next();
            String differences = "";
            
            for(int index = 0; index < firstSequence.length(); index++) {

                char firstIndexCharacter = firstSequence.charAt(index);
                char secondIndexCharacter = secondSequence.charAt(index);

                if (firstIndexCharacter == secondIndexCharacter) {
                    differences += ".";
                } else {
                    differences += "*";
                }
            }

            System.out.println(firstSequence);
            System.out.println(secondSequence);
            System.out.println(differences);
            System.out.println();
        
        }
    }
}