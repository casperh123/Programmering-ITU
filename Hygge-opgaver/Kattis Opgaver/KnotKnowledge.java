import java.util.Scanner;

public class KnotKnowledge {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        int[] knotsToLearn = new int[testCases];
        int[] knownKnots = new int[testCases - 1];

        for (int i = 0; i < testCases; i++) {
            knotsToLearn[i] = input.nextInt();
        }
        for (int i = 0; i < testCases - 1; i++) {
            knownKnots[i] = input.nextInt();
        }

        for (int i = 0; i < testCases; i++) {

            boolean numberFound = false;

            for (int index = 0; index < testCases - 1; index++) {
                if (knotsToLearn[i] == knownKnots[index]) {
                    numberFound = true;
                }
            }

            if(!numberFound) {
                System.out.println(knotsToLearn[i]);
            }
        }
    }
}