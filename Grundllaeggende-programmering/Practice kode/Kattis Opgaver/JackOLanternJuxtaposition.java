import java.util.Scanner;

public class JackOLanternJuxtaposition {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int combinationSum = input.nextInt();

        for (int i = 0; i < 2; i++) {
            combinationSum *= input.nextInt();
        }

        System.out.println(combinationSum);
    }
}
