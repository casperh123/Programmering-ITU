import java.util.Scanner;

public class Relocation {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);


        int companies = input.nextInt();
        int appQueries = input.nextInt();
        int[] companyLocation = new int[companies];

        for (int i = 0; i < companies; i++) {
            companyLocation[i] = input.nextInt();
        }

        for (int i = 0; i < appQueries; i++) {
            
            int queryIdentifier = input.nextInt();

            if (queryIdentifier == 1) {

                int companyToRelocate = input.nextInt();
                int relocateTo = input.nextInt();
    
                companyLocation[companyToRelocate - 1] = relocateTo;

            } 
            
            if (queryIdentifier == 2) {
    
                int firstCompany = input.nextInt();
                int secondCompany = input.nextInt();
                int distance = Math.abs(companyLocation[secondCompany - 1] - companyLocation[firstCompany - 1]);
    
                System.out.println(distance);
            }
        }
    }
}
