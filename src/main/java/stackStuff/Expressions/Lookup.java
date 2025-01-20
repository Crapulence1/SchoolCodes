package stackStuff.Expressions;

public class Lookup {
    public int getIn(String token){
        return switch (token) {
            case "+", "-" -> 1;
            case "*", "/" -> 3;
            case "^" -> 6;
            case "(" -> 10;
            case ")" -> 0;
            default -> 0;
        };
    }
    public int getStack(String token){
        return switch (token) {
            case "+", "-" -> 2;
            case "*", "/" -> 4;
            case "^" -> 5;
            case "(" -> 0;
            case ")" -> -1;
            case null -> -1;
            default -> 0;
        };
    }
    public char looksLike(String token){
        return switch (token) {
            case "+", "-", "*", "/", "^", ")" -> 'R';
            default -> 'D';
        };
    }
    public char expects(String token){
        return switch (token) {
            case "+", "-", "*", "/", "^", "(" -> 'D';
            default -> 'R';
        };
    }
    public boolean isOutputable(String token){
        return switch (token) {
            case "+", "-", "*", "/", "^" -> true;
            case ")", "(" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + token);
        };
    }
}
