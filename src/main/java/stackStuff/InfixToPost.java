package stackStuff;

import java.util.Arrays;
import java.util.Scanner;
import static stackStuff.PostfixEval.postfix;
public class InfixToPost {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        try {
            String[] answer = InfixConvert(input);
            double eval = postfix(answer);
            print(answer);
            System.out.println(eval);
        }
        catch (NullPointerException e){ //catches if we have incorrect number of parentheses
            System.out.println("Failed: Incorrect number of parentheses");
            print(input);
        }
        catch(Exception e){ //catches every other error and displays their message
            System.out.print("Failed: ");
            System.out.println(e.getMessage());
            print(input);

        }
    }
    public static String[] InfixConvert(String[] input) throws Exception {
        Lookup table = new Lookup();
        AStack stack = new AStack(input.length);
        String[] ans = new String[input.length];
        char expected = 'D';
        int ansCount = 0;
        for(String token : input) {
            if(!isNum(token)){
                if(table.looksLike(token)==expected){
                    if(!token.equals(")")) {
                        expected = (expected == 'R' || token.equals("(")) ? 'D' : 'R'; //Swaps the expected token
                    }
                    if(token.equals(")")) {
                        while (!stack.peek().equals("(")) {
                            ans[ansCount++] = (String) stack.pop();  // Pop operators to the output
                        }
                        stack.pop();  // Discard the opening parenthesis
                    } else {
                        while (!stack.isEmpty() && table.getIn(token) <= table.getStack((String) stack.peek())) { //while the top element of stack has higher precedence than inputted operator, pop the top element
                            ans[ansCount++] = (String) stack.pop();
                        }
                        stack.push(token);
                    }
                } else {
                    throw new Exception("Improper formatting at " + token);
                }
            } else {
                expected = (expected == 'R' || token.equals("(")) ? 'D' : 'R'; //Swaps the expected token
                ans[ansCount++] = token; //adds the current token to the answer
            }
        }
        while (!stack.isEmpty()){//flushes the stack once we reach the end of the loop
            if(table.isOutputable((String) stack.peek())) { //checks if the top of the sta k is outputable
                ans[ansCount++] = (String) stack.pop(); //adds it to the answer if it is
            } else {

                if(stack.pop().equals("(")){ //if we have to many open parentheses throw error, else just pop
                    throw new NullPointerException();
                }
            }
        }
        return ans; //gets rid of all elements in stack and sends it to postfix expression in proper order and returns answer
    }

    public static boolean isNum(String check){ //checks if the current element is a number
        try{
            int x = Integer.parseInt(check);
            if(check.equals("(") || check.equals(")")) return false;
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static void print(String[] input){
        for(int i = 0; i < input.length && input[i] != null; i++){
            System.out.print(input[i] + " ");
        }
        System.out.println();
    }
}