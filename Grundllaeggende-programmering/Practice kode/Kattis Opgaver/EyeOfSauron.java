import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class EyeOfSauron {
    public static void main(String[] args) {

        List<Character> charStack = new ArrayList<>();
        Boolean balanced = false;

        Scanner input = new Scanner(System.in);
        
        String inputString = input.nextLine();

        for(int i = 0; i < inputString.length(); i++) {

            balanced = false;

            char operand = inputString.charAt(i);

            if(operand == '(' || operand == '|') {
                push(operand, charStack);
                continue;
            } else if(!(charStack.size() == 0)) {
                switch(operand) {
                    case ')':
                        balanced = pop(charStack) == '(';
                        break;
                    case '|':
                        balanced = pop(charStack) == '|';
                        break;
                }
            } else {
                balanced = false;
                break;
            }
        }

        if(!balanced) {
            System.out.println("fix");
        } else {
            System.out.println("correct");
        }
    }

    public static void push(char operand, List<Character> charStack) {
        charStack.add(operand);
    }

    public static char pop(List<Character> charStack) {
        char tempChar = charStack.get(charStack.size()-1);
        charStack.remove(charStack.size()-1);
        return tempChar;
    }
}
