import java.util.Scanner;

public class Greetings {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);


        String initialGreeting = input.nextLine();
        String modifiedGreeting = "";
        int modifiedGreetingLength = (initialGreeting.length() - 2) * 2;

        for (int i = 0; i < modifiedGreetingLength; i++) {
            modifiedGreeting += "e";
        }

        System.out.println("h" + modifiedGreeting + "y");
    }
}
