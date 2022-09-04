import java.util.Scanner;

public class Zamka {
    
    public static int sumDigits(int inputInteger) {
        
        int summedInteger = 0;


        while (inputInteger > 0) {

                summedInteger += inputInteger % 10; 
                inputInteger /= 10;

        }

        return summedInteger;

    }

    public static void main(String args[]) {
        
        Scanner input = new Scanner(System.in);
        
        int lowerBound = input.nextInt();
        int upperBound = input.nextInt();
        int digitSumComparator = input.nextInt();

        int highestSum = 0;
        int lowestSum = 0;

        for (int i = upperBound; i >= lowerBound; i--) {

            int sumOfIndex = sumDigits(i);

            if (sumOfIndex == digitSumComparator && lowestSum == highestSum) {
                highestSum = i;
            } else if (sumOfIndex == digitSumComparator && sumOfIndex <= highestSum) {
                lowestSum = i;
            }
        }

        System.out.println(lowestSum > lowerBound ? lowestSum : lowerBound);
        System.out.println(highestSum > lowerBound ? highestSum : lowerBound);
    }
}
