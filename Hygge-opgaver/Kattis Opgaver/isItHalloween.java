import java.util.Scanner;

public class isItHalloween {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String date = input.nextLine();
        String halloweenDateOct = "OCT 31";
        String halloweenDateDec = "DEC 25";

        input.close();

        if (halloweenDateOct.equals(date) || halloweenDateDec.equals(date)) {
            System.out.println("yup");
        } else {
            System.out.println("nope");
        }
    }
}