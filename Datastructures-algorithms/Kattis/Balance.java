import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Balance {
    public static void main(String[] args) {

        Stack<Character> charStack = new Stack<>();
        Boolean balanced = false;

        String inputString = StdIn.readAll();

        for(int i = 0; i < inputString.length(); i++) {

            balanced = false;;

            char operand = inputString.charAt(i);

            if(operand == '(' || operand == '[') {
                charStack.push(operand);
            } else if(!charStack.isEmpty()) {
                switch(operand) {
                    case ')':
                        balanced = charStack.pop() == '(';
                        break;
                    case ']':
                        balanced = charStack.pop() == '[';
                        break;
                }
            }

        }

        if(!charStack.isEmpty() || !balanced) {
            StdOut.println(0);
        } else {
            StdOut.println(1);
        }
    }
}
