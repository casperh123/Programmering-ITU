import java.util.Scanner;
import java.util.ArrayList;

public class IveBeenEveryWhereMan {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int totalCases = input.nextInt();

        for(int i = 0; i < totalCases; i++) {

            int citiesToCycle = input.nextInt();
            String[] visitedCities = new String[citiesToCycle];
            ArrayList<String> uniqueCities = new ArrayList<>();

            for (int index = 0; index < citiesToCycle; index++) {
                visitedCities[index] = input.next();
            }

            for (String visitedCity : visitedCities) {

                boolean seenBefore = false;

                for (String uniqueCity : uniqueCities) {
                    
                    if(visitedCity.equals(uniqueCity)) {
                        
                        seenBefore = true;
                        break;
                    
                    };
                
                }

                if(!seenBefore)
                    uniqueCities.add(visitedCity);
            }

            System.out.println(uniqueCities.size());

        }
    }
}
