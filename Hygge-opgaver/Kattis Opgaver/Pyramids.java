import java.util.Scanner;

public class Pyramids {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int stones = input.nextInt();
        int cumulativeStones = 0;
        int height = 0;
        int i = 1;

        while(cumulativeStones < stones) {

            cumulativeStones += i * i;
            height++;
            i += 2;

        }

        System.out.println(cumulativeStones > stones ? height - 1 : height);

    } 
}
