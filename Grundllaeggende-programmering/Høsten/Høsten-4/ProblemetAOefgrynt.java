import java.util.Scanner;

public class ProblemetAOefgrynt {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int repititions = input.nextInt();

        for(int i = 0; i < repititions; i++) {

            int numberToOutput = i % 100 + 1;
            int animalTimer = i + 1;
            
            if (animalTimer % 3 == 0) {
                System.out.print("øf");
            } 
            if (animalTimer % 5 == 0) {
                System.out.print("grynt");
            } 
            if (animalTimer % 3 != 0 && animalTimer % 5 != 0) {
                System.out.print(numberToOutput);
            }

            System.out.println();
        }
        

    }
}
