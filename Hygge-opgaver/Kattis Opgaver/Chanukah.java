import java.util.Scanner;

public class Chanukah {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        int setSize = input.nextInt();

        for (int i = 1; i <= setSize; i++) {

            int dataIndex = input.nextInt();
            int days = input.nextInt();
            int candles = 0;

            for(int dataSetIndex = 1; dataSetIndex <= days; dataSetIndex++){

                candles += dataSetIndex;
                candles++;
            }

            System.out.println(dataIndex + " " + candles);
        }
    }
}
