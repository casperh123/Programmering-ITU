import java.util.Scanner;

public class CPRnummber {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int[] checkSumMultipliers = {4, 3, 2, 7, 6, 5, 4, 3, 2, 1};
        String[] cprNummer = input.nextLine().replace("-", "").split("");
        int checkSum = 0;

        for(int i = 0; i < cprNummer.length; i++) {
            checkSum += checkSumMultipliers[i] * Integer.parseInt(cprNummer[i]); 
        }

        System.out.println(checkSum % 11 == 0? 1 : 0);
    
    }
}
