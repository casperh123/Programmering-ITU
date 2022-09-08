import java.util.Scanner;

public class Vindmoelle {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int startPosition = input.nextInt();
        int slutPosition = input.nextInt();
        int angleDifference = startPosition > slutPosition ? startPosition - slutPosition : slutPosition - startPosition;

        if (angleDifference <= 180) {
            System.out.println(slutPosition - startPosition != -180 ? slutPosition - startPosition : 180);
        } else if(angleDifference > 180 && startPosition > slutPosition) {
            System.out.println(360 - startPosition + slutPosition);
        } else if(angleDifference > 180 && startPosition < slutPosition) {
            System.out.println(slutPosition - 360 - startPosition);
        }
    }
}