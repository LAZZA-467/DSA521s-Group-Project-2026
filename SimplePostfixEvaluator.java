import java.util.Stack;
public class SimplePostfixEvaluator {
    public static int evaluate(String expression) {
        Stack<Integer> stack = new Stack<>();
        for (String token : expression.split("\\s+")) {
            if (token.isEmpty()) continue;

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("÷") || token.equals("/")) {
                int rightOperand = stack.pop();
                int leftOperand = stack.pop();
                int result = 0;

                switch (token) {
                    case "+": result = leftOperand + rightOperand; 
                    break;
                    case "-": result = leftOperand - rightOperand; 
                    break;
                    case "*": result = leftOperand * rightOperand; 
                    break;
                    case "÷":
                    case "/": result = leftOperand / rightOperand; 
                    break;
                }
                stack.push(result);
            } 
            else {
                stack.push(Integer.parseInt(token));
            }
            System.out.println("Current top: " + stack.peek());
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        String expression = "5 3 + 2 *";
        System.out.println("Expression: " + expression);
        System.out.println("Final Result: " + evaluate(expression));
    }
}
