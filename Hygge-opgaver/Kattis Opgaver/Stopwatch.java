import java.util.Scanner;

public class Stopwatch {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int buttonPresses = input.nextInt();
        int timedSeconds = 0;

        if (!(buttonPresses % 2 == 0)) {
            System.out.println("still running");
            return;
        } else {
            for (int i = 0; i < buttonPresses; i = i + 2) {
                
                int firstPress = input.nextInt();
                int secondPress = input.nextInt();
    
                timedSeconds += Math.abs(secondPress - firstPress);
            }
        }

        System.out.println(timedSeconds);

    }
}
