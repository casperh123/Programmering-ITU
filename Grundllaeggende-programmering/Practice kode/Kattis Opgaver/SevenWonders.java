import java.util.Scanner;

public class SevenWonders {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int tabletCards = 0;
        int compassCards = 0;
        int gearCards = 0;
        int totalScore = 0;

        String[] cards = input.nextLine().split("");

        for(int i = 0; i < cards.length; i++) {
            switch(cards[i]) {
                case "T":
                    tabletCards++;
                    break;
                case "C":
                    compassCards++;
                    break;
                case "G":
                    gearCards++;
                    break;
            }
        }

        totalScore += Math.pow(tabletCards, 2) + Math.pow(compassCards, 2) + Math.pow(gearCards, 2);

        while (tabletCards > 0 && compassCards > 0 && gearCards > 0) {
            totalScore += 7;
            tabletCards--;
            compassCards--;
            gearCards--;
        }

        System.out.println(totalScore);
        
    }
}