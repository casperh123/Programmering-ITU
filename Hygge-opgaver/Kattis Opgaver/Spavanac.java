import java.util.Scanner;

public class Spavanac {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int timeHour = input.nextInt();
        int timeMinute = input.nextInt();
        
        if (timeMinute - 45 < 0) {
            timeMinute = timeMinute - 45 + 60;
            timeHour--;
        } else {
            timeMinute = timeMinute - 45;
        }

        if (timeHour < 0)
            timeHour += 24;
        System.out.print(timeHour + " " + timeMinute);
    }
}
