import java.util.Scanner;

public class Avion {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        boolean spyFound = false;

        for(int i = 0; i < 5; i++) {

            String encodedString = input.next();
            int stringLength = encodedString.length();

            for(int index = 0; index <= stringLength - 3; index++) {
                if(encodedString.substring(index, index+3).equals("FBI")) {
                    System.out.println(i + 1);
                    spyFound = true;
                    break;
                } 
            }

        }

        if(!spyFound) {
            System.out.println("HE GOT AWAY!");

        }
    }    
}
