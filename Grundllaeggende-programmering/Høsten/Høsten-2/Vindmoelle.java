import java.util.Scanner;

public class Vindmoelle {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int startPosition = input.nextInt();
        int slutPosition = input.nextInt();
        int angleDifference;

        if(startPosition > slutPosition) {
            angleDifference = startPosition - slutPosition;
        } else {
            angleDifference = slutPosition - startPosition;
        }

        if (angleDifference <= 180) {
            System.out.println(slutPosition - startPosition != -180 ? slutPosition - startPosition : 180);
        } else if(angleDifference > 180 && startPosition > slutPosition) {
            System.out.println(startPosition + slutPosition - 360 );
        } else if(angleDifference > 180 && startPosition < slutPosition) {
            System.out.println(slutPosition - 360 - startPosition);
        } 
    }
}
