import java.util.Scanner;

public class EncodedMessage {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int timesRun = 0; timesRun < testCases; timesRun++) {
            
            String unsortedString = input.next();
            String sortedString = "";
            int stringIterations = (int)Math.round(Math.sqrt(unsortedString.length())) - 1;
            int charactersToSkip = stringIterations + 1;

            for(int i = stringIterations; i >= 0; i--) {
                for(int index = i; index < unsortedString.length(); index += charactersToSkip) {
                    sortedString += unsortedString.charAt(index);
                }
            }
    
            System.out.println(sortedString);
        }
    }
}