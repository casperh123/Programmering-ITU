import java.util.Scanner;

public class CountTheVowels {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] inputString = input.nextLine().toLowerCase().split("");
        int vowelsTotal = 0;

        for(String singleChar : inputString) {
            if(singleChar.matches("[aeiou]")) {
                vowelsTotal++;
            }
        }

        System.out.println(vowelsTotal);

    }
}
