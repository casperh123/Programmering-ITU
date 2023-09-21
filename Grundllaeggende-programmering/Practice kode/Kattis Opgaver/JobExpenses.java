import java.util.Scanner;

public class JobExpenses {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();
        int expensesSum = 0;

        for (int i = 0; i < testCases; i++) {
            
            int caseTal = input.nextInt();
            
            if(caseTal < 0) 
                expensesSum += Math.abs(caseTal);
        }

        System.out.println(expensesSum);
        
    }
}
