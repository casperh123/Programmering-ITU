import java.util.*;

public class Apaxiaaaaaaaaaaaans {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String inputString = input.next();

        int stringLength = inputString.length();

        List<Character> sanitisedString = new LinkedList<>();

        for(int i = 0; i < stringLength; i++) {
            sanitisedString.add(inputString.charAt(i));
        }

        for(int i = 0; i < stringLength; i++) {
            while(i + 1 < sanitisedString.size() && sanitisedString.get(i) == sanitisedString.get(i + 1)) {
                sanitisedString.remove(i + 1);
            }
        }

        for(Character index : sanitisedString) {
            System.out.print(index);
        }

    }
}
