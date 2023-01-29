import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class DrinkMenu {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] drinksArr = input.nextLine().split(" ");
        int numberOfDrinks = Integer.parseInt(drinksArr[0]);
        int testCases = Integer.parseInt(drinksArr[1]);


        ArrayList<String> drinks = new ArrayList<>();
        HashMap<String, Integer> guestServings = new HashMap<>();

        for(int i = 0; i < numberOfDrinks; i++) {
            drinks.add(input.nextLine());
        }

        for(int i = 0; i < testCases; i++) {

            String guest = input.nextLine();

            if(!guestServings.containsKey(guest)) {
                System.out.println(drinks.get(0));
                guestServings.merge(guest, 1, (a, b) -> a + b);
            } else {
                System.out.println(drinks.get(guestServings.get(guest)));
                guestServings.merge(guest, 1, (a, b) -> a + b);
            }
        }

    }
}
