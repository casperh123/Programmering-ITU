import java.util.Scanner;

public class ProvincesAndGold {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int goldCards = input.nextInt();
        int silverCards = input.nextInt();
        int copperCards = input.nextInt();
        int totalBuyingPower = goldCards * 3 + silverCards * 2 + copperCards * 1;

        String bestVictoryCard = "";
        String bestTreasureCard = "";

        if(totalBuyingPower >= 0 && totalBuyingPower < 2) {
            bestTreasureCard = "Copper";
        } else if (totalBuyingPower >= 2 && totalBuyingPower < 3) {
            bestTreasureCard = "Copper";
            bestVictoryCard = "Estate";
        } else if (totalBuyingPower >= 3 && totalBuyingPower < 5) {
            bestTreasureCard = "Silver";
            bestVictoryCard = "Estate";
        } else if (totalBuyingPower >= 5 && totalBuyingPower < 6) {
            bestTreasureCard = "Silver";
            bestVictoryCard = "Duchy";
        } else if (totalBuyingPower >= 6 && totalBuyingPower < 8){
            bestTreasureCard = "Gold";
            bestVictoryCard = "Duchy";
        } else {
            bestTreasureCard = "Gold";
            bestVictoryCard = "Province";
        }

        System.out.println(bestVictoryCard.equals("") ? bestTreasureCard : bestVictoryCard + " or " + bestTreasureCard);
    }
}
