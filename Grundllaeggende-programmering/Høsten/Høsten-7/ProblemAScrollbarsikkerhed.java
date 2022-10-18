import java.util.Scanner;

public class ProblemAScrollbarsikkerhed {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int maxGuests = 0;
        int guestsIn = 0;
        int guestsOut = 0; 

        while (input.hasNextLine()) {

            String currentInput = input.nextLine();

            if(currentInput.equals("Gæst ind")) {
                
                guestsIn++;

                if(guestsOut > 0) {
                    guestsOut--;
                }

            } else {

                guestsOut++;

                if(guestsIn > 0) {
                    guestsIn--;
                }
            }

            if(guestsIn > maxGuests) {
                maxGuests = guestsIn;
            } else if(guestsOut > maxGuests){
                maxGuests = guestsOut;
            }
        }

        

        System.out.println(maxGuests);
        
    }
}