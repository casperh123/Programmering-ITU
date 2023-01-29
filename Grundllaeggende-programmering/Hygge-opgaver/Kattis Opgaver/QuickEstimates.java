import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class QuickEstimates {
    
    public static void main(String[] args) {
        
        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(inputStream);

        int testCases = Integer.parseInt(input.readLine());

        for(int i = 0; i < testCases; i++) {

            int digits = 0;
            
            int numberToEstimate = input.nextInt();

            if(numberToEstimate == 0) {
                digits++;
            } else {
                while(numberToEstimate > 0) {
                    digits++;
                    numberToEstimate /= 10;
                }
            }

            System.out.println(digits);
        }


    }
}
