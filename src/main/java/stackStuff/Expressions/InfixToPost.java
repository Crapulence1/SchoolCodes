package stackStuff.Expressions;
import static stackStuff.Expressions.PostfixEval.postfix;
public class InfixToPost {
    public static void main(String[] args) throws Exception {
        for (String expression : args) {
            String[] input = expression.split(" ");
            try {
                String[] answer = InfixConvert(input);
                double eval = postfix(trim(answer));
                print(answer);
                System.out.println(eval);
            } catch (NullPointerException e) { //catches if we have incorrect number of parentheses
                System.out.println("Failed: Incorrect number of parentheses");
                print(input);
            } catch (Exception e) { //catches every other error and displays their message
                System.out.print("Failed: ");
                System.out.println(e.getMessage());
                print(input);
            }
        }
    }
    public static String[] InfixConvert(String[] input) throws Exception {
        Lookup table = new Lookup();                //creates the lookup table
        InStack stack = new InStack(input.length);    //creates the operator stack
        String[] ans = new String[input.length];    //our final answer
        char expected = 'D';                    //checks if the current token is operand - 'D' or operator - 'R'
        int ansCount = 0;               //tracks the current index of what we put into the answer
        for(String token : input) {
            if(!isNum(token)){//checks if the current token is a number
                if(table.looksLike(token)==expected){
                    if(!token.equals(")")) {
                        expected = table.expects(token); //Swaps the expected token
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
                expected = table.expects(token); //Swaps the expected token
                ans[ansCount++] = token; //adds the current token to the answer
            }
        }
        while (!stack.isEmpty()){//flushes the stack once we reach the end of the loop
            if(table.isOutputable(stack.peek())) { //checks if the top of the sta k is output able
                ans[ansCount++] = stack.pop(); //adds it to the answer if it is
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
            double x = Double.parseDouble(check);    //tries to parse into an integer
            return !check.equals("(") && !check.equals(")");
        }
        catch (Exception e){
            return false;
        }
    }

    public static String[] trim(String[] input){
        int i = 0;
        for(String datum: input){
            if (datum!=null) i++;
        }
        String[] answer = new String[i];
        System.arraycopy(input, 0, answer, 0, answer.length);
        return answer;
    }

    public static void print(String[] input){ //prints the answer as an expression instead of an array
        for(int i = 0; i < input.length && input[i] != null; i++){
            System.out.print(input[i] + " ");
        }
        System.out.println();
    }
}