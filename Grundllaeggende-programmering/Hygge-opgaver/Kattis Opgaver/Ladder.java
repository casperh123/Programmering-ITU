import java.util.Scanner;

public class Ladder {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int wallHeight = input.nextInt();
        int oppositeAngle = input.nextInt();
        int ladderHeight = (int) Math.ceil(wallHeight / Math.sin(Math.toRadians(oppositeAngle)));

        System.out.println(ladderHeight);
    }
}
