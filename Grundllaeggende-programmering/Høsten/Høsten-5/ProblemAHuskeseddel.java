import java.util.Scanner;

public class ProblemAHuskeseddel {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String pinString = input.next();

        int pinLength = pinString.length();
        int[] reversePinArray = new int[pinLength];

        for(int i = 0; i < pinLength; i++) {
            reversePinArray[i] = Character.getNumericValue(pinString.charAt(i));
        }
        
        if (reversePinArray[3] == 9){
            
            for(int i = 3; i > 0; i--) {
                
                if(reversePinArray[i] == 9) {
                    
                    reversePinArray[i] = 0;
                    
                    if(reversePinArray[i - 1] == 9) {
                        reversePinArray[i - 1] = 0;
                    } else {
                        reversePinArray[i - 1] = reversePinArray[i - 1] + 1;
                    }
                }
            }  
        } else {
            reversePinArray[3] = reversePinArray[3] + 1;
        }

        for(int i = pinLength - 1; i >= 0; i--) {
            System.out.print(reversePinArray[i]);
        }
    }
}