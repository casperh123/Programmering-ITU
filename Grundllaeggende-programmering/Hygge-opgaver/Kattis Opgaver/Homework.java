import java.util.Scanner;
import java.util.ArrayList;

public class Homework {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] inputList = input.next().split(";");

        ArrayList<String> unsanitisedList = new ArrayList<>();
        ArrayList<String> sanitisedList = new ArrayList<>();

        for(int i = 0; i < inputList.length; i++) {
            if(inputList[i].contains("-")) {
                unsanitisedList.add(inputList[i]);
            } else {
                sanitisedList.add(inputList[i]);
            }
        }

        for(String index : unsanitisedList) {

            int seperatorIndex = index.indexOf("-");
            int startIndex = Integer.parseInt(index.substring(0, seperatorIndex));
            int endIndex = Integer.parseInt(index.substring(seperatorIndex + 1, index.length()));


            for(int i = startIndex; i <= endIndex; i++) {
                sanitisedList.add(Integer.toString(i));
            }
        }

        System.out.println(sanitisedList.size());
    }
}