import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    public double calculate(Deque<String> reversePolishQueue) {
        Deque<String> numberStack = new ArrayDeque<>();

        while (!reversePolishQueue.isEmpty()) {
            String token = reversePolishQueue.pollFirst();
            double right = 0.0;
            double left = 0.0;
            double result = 0.0;

            if (token.matches("\\d+(\\.\\d+)?")) {
                numberStack.offerFirst(token);
                continue;
            }

            switch (token) {
                case "+":
                    right = Double.parseDouble(numberStack.pollFirst());
                    left = Double.parseDouble(numberStack.pollFirst());
                    result = left + right;
                    numberStack.offerFirst(String.valueOf(result));
                    break;

                case "-":
                    right = Double.parseDouble(numberStack.pollFirst());
                    left = Double.parseDouble(numberStack.pollFirst());
                    result = left - right;
                    numberStack.offerFirst(String.valueOf(result));
                    break;

                case "*":
                    right = Double.parseDouble(numberStack.pollFirst());
                    left = Double.parseDouble(numberStack.pollFirst());
                    result = left * right;
                    numberStack.offerFirst(String.valueOf(result));
                    break;

                case "/":
                    right = Double.parseDouble(numberStack.pollFirst());
                    left = Double.parseDouble(numberStack.pollFirst());
                    result = left / right;
                    numberStack.offerFirst(String.valueOf(result));
                    break;

                default:
                    break;
            }
        }

        return Double.parseDouble(numberStack.pollFirst());
    }
}
