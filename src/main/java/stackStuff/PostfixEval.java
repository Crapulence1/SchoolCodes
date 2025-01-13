package stackStuff;
import java.util.Scanner;
public class PostfixEval {
    public static void main(String [] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        System.out.println(postfix(input));
    }
    public static double postfix(String[] input) throws Exception{
        AStack stack = new AStack(input.length);
        double a;
        double b;
        for (String token : input) {
            switch (token) {
                case "+":
                    a = (double) stack.pop();
                    b = (double) stack.pop();
                    stack.push(b + a);
                    break;
                case "-":
                    a = (double) stack.pop();
                    b = (double) stack.pop();
                    stack.push(b - a);
                    break;
                case "*":
                    a = (double) stack.pop();
                    b = (double) stack.pop();
                    stack.push(b * a);
                    break;
                case "/":
                    a = (double) stack.pop();
                    b = (double) stack.pop();
                    if (a == 0) throw new Exception("Division by zero"); //if we divide by zero
                    stack.push(b / a);
                    break;
                case "^":
                    a = (double) stack.pop();
                    b = (double) stack.pop();
                    if(b < 0) throw new Exception("Exponentiation with a negative base"); // if the base is negative throw exception
                    stack.push(Math.pow(b, a));
                    break;
                default:
                    stack.push(Double.parseDouble(token));
                    break;
            }
        }
        return (double) stack.pop();
    }
}
