import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class HairOfADog {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = Integer.parseInt(input.nextLine());

        int daysHungover = 0;

        ArrayList<String> dayStatus = new ArrayList<>();

        for(int i = 0; i < testCases; i++) {

            dayStatus.add(input.nextLine());

        }

        for (int i = 0; i < testCases; i++) {

            if(i == dayStatus.size() - 1) {
                break;
            } else {
                if(dayStatus.get(i).equals("drunk") && dayStatus.get(i + 1).equals("sober")) {
                    daysHungover++;
                }
            }

        }

        System.out.println(daysHungover);


    }
}
