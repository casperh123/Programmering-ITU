import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Balance {
    public static void main(String[] args) {

        Stack<Character> charStack = new Stack<>();
        
        Boolean balanced = false;

        String inputString = StdIn.readString();

        if(!(inputString.length() % 2 == 0)) {
            StdOut.println(0);
            return;
        }
        
        for(Character operand : inputString.toCharArray()) {

            balanced = false;

            if((operand == ')' || operand == ']') && charStack.isEmpty()) {
                balanced = false;
                break;
            } else if(operand == '(' || operand == '[') {
                charStack.push(operand);
                continue;
            } else if(charStack.peek() == '(' && operand == ')' || charStack.peek() == '[' && operand == ']') {
                balanced = true;
                charStack.pop();
            } else {
                balanced = false;
                break;
            }
        }

        if(!charStack.isEmpty()) {
            balanced = false;
        }

        if(balanced) {
            StdOut.print(1);
        } else {
            StdOut.print(0);
        }
    }
}
