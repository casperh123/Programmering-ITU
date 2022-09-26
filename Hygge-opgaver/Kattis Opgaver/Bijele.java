import java.util.Scanner;

public class Bijele {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int[] completeSet = new int[]{1, 1, 2, 2, 2, 8};
        int[] amountInSet = new int[6];

        for(int i = 0; i < 6; i++) {

            int comparator = input.nextInt();

            amountInSet[i] = completeSet[i] - comparator;

        }

        for(int piecesToModify : amountInSet) {
            System.out.println(piecesToModify);
        }

        
    }
}