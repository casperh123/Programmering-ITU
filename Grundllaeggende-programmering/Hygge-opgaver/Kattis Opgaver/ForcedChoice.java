import java.util.Scanner;

public class ForcedChoice {
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();
        int pinkButtons = 0;

        for (int i = 0; i < testCases; i++) {

            String buttonColour = input.next().toLowerCase();

            if (buttonColour.indexOf("rose") > -1 || buttonColour.indexOf("pink") > -1) {
                pinkButtons++;
            }
        }

        System.out.println(pinkButtons > 0 ? pinkButtons : "I must watch Star Wars with my daughter");
    }
}
