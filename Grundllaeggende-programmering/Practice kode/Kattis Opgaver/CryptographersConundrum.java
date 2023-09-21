import java.util.Scanner;

public class CryptographersConundrum {
    
    public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);

    int similarities = 0;
    String inputString = input.next().toLowerCase();
    String comparatorString = "Per".toLowerCase();


    for (int i = 0; i < inputString.length(); i++) {

        int indexCounter = i % 3;

        if(inputString.charAt(i) == comparatorString.charAt(indexCounter)) {
            similarities++;
        }
    }

    System.out.println(inputString.length() - similarities);
    }
}
