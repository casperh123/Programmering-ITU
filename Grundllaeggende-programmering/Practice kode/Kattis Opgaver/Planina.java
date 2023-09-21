import java.util.Scanner;

public class Planina {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int iterations = (int) Math.pow(2, input.nextInt());
        int pointSum = 0;

        for (int i = iterations; i > 0; i -= 2) {
            pointSum += i * 4;
        }

        pointSum += 1;
    
        System.out.println(pointSum);
    }
}
