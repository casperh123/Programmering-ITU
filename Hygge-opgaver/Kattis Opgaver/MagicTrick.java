import java.util.Scanner;

public class MagicTrick {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int duplicateChars = 0;
        String[] cardSequence = input.nextLine().split("");


        for (int i = 0; i < cardSequence.length; i++) {

            String comparator = cardSequence[i];
            
            for (int index = 0; index < cardSequence.length; index++){
                
                if (i == index) {
                    continue;
                }
                if (comparator.equals(cardSequence[index])) {
                    duplicateChars++;
                    break;
                } 

            }

        }

        System.out.println(duplicateChars < 2 ? 1 : 0);
    }
}
