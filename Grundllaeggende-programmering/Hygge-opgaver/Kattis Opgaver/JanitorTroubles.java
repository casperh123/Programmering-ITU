import java.util.Scanner;

public class JanitorTroubles {
    public static void main(String[] args) {
        
    Scanner input = new Scanner(System.in);

    int s1 = input.nextInt();
    int s2 = input.nextInt();
    int s3 = input.nextInt();
    int s4 = input.nextInt();

    double semiperimeter = (s1 + s2 + s3 + s4) / 2.0;
    double maxArea = Math.sqrt((semiperimeter - s1) * 
                                (semiperimeter - s2) * 
                                (semiperimeter - s3) * 
                                (semiperimeter - s4));

    System.out.println(maxArea);

    }
}
