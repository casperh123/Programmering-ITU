import java.util.Scanner;

public class TurnItUp {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = Integer.parseInt(input.nextLine());
        int currentVolume = 7;

        for(int i = 0; i < testCases; i++) {

            String volumeControl = input.nextLine();

            if(currentVolume > 0 && currentVolume < 10) {
                if(volumeControl.equals("Skru op!")) {
                    currentVolume++;
                } else {
                    currentVolume--;
                }
            } else if(currentVolume == 0 && volumeControl.equals("Skru op!")) {
                currentVolume++;
            } else if(currentVolume == 10 && volumeControl.equals("Skru ned!")) {
                currentVolume--;
            }
        }

        System.out.println(currentVolume);

    }

}
