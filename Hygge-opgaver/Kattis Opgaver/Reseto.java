import java.util.Scanner;
import java.util.ArrayList;

public class Reseto {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int upTo = input.nextInt();
        int stopTurn = input.nextInt();
        int iterations = 0;
        ArrayList<Integer> integerList = new ArrayList<>();

        for (int i = 0; i <= upTo - 2; i++) {
            integerList.add(i+2);
        }

        while(iterations < stopTurn) {

            int divisor = integerList.get(0);

            for(int i = 0; i < integerList.size(); i++) {
                if(iterations == stopTurn) {
                    System.out.println(integerList.get(i));
                } else if(integerList.get(i) % divisor == 0) {
                    integerList.remove(i);
                    iterations++;
                    i--;
                }
            }
        }
    }
}
