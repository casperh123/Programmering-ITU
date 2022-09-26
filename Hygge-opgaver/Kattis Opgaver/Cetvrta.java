
import java.util.*;

public class Cetvrta {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int[] knownX = new int[3];
        int[] knownY = new int[3];

        List<Integer> sortedX = new ArrayList<>();
        List<Integer> sortedY = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            knownX[i] = input.nextInt();
            knownY[i] = input.nextInt();
        }

        sortedX.add(knownX[0]);
        sortedY.add(knownY[0]);


        if(knownX[0] == knownX[1]) {
            sortedX.add(knownX[1]);
            sortedY.add(knownY[1]);
            sortedX.add(knownX[2]);
            sortedY.add(knownY[2]);
            } else {
            sortedX.add(knownX[2]);
            sortedY.add(knownY[2]);
            sortedX.add(knownX[1]);
            sortedY.add(knownY[1]);
        }

        int X = sortedX.get(0) - sortedX.get(1);
        int Y = sortedY.get(0) - sortedY.get(1);

        int pointX = X + sortedX.get(2);
        int pointY = Y + sortedY.get(2);

        System.out.println(pointX + " " + pointY);


    }
}