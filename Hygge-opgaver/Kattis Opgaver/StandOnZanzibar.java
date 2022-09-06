import java.util.Scanner;
import java.util.ArrayList;

public class StandOnZanzibar {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for (int i = 0; i < testCases; i++) {
            
            ArrayList<Integer> yearArray = new ArrayList<Integer>();
            boolean endLine = false;
            int index = 0;
            int importetTurtles = 0;

            while(!endLine) {
                
                int tal = input.nextInt();
                
                if(tal == 0) {
                    endLine = true;
                    break;
                }

                yearArray.add(tal);

                index++;
            }

            for (int year : yearArray) {
                System.out.println("Index " + year);
            }
            
            for(int yearIndex = 0; yearIndex < (yearArray.size() - 1); yearIndex++) {

                if(yearArray.get(yearIndex + 1) > yearArray.get(yearIndex) * 2) {

                    int leftOverTurtles = yearArray.get(yearIndex + 1) - yearArray.get(yearIndex) * 2;

                    importetTurtles = leftOverTurtles;

                    System.out.println(yearArray.get(yearIndex));

                }
                
                
            }

            System.out.println("Der er " + importetTurtles + " turtles");

        }
    }
}
