import java.util.Scanner;

public class Pokechat {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] inputString = input.nextLine().split("");
        String[] decodingString = input.nextLine().split("");

        String decodedString = "";

        for(int index = 0; index < decodingString.length - 2; index += 3) {
            
            int calculatedIndex = 0;

            for(int i = index; i <= index+2 && i <= decodingString.length; i++) {
            
                int indexInteger = Integer.parseInt(decodingString[i]);
    
                if(i % 3 == 0) {
                    calculatedIndex += indexInteger * 100;
                } else if (i % 3 == 1) {
                    calculatedIndex += indexInteger * 10;
                } else if (i % 3 == 2) {
                    calculatedIndex += indexInteger;
                }
            }

            decodedString += inputString[calculatedIndex-1];

        }

        System.out.println(decodedString);
    }
}
