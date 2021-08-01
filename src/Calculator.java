import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Calculator {
    public double calculate(Deque<String> reversePolishQueue, Map<String, OperatorAttribute> operators) throws OperatorUndefinedExeption {
        Deque<String> numberStack = new ArrayDeque<>();

        while (!reversePolishQueue.isEmpty()) {
            String token = reversePolishQueue.pollFirst();

            // 数字の場合スタックに積む
            if (token.matches("\\d+(\\.\\d+)?")) {
                numberStack.offerFirst(token);
                continue;
            }

            try {
                // そうでない場合，演算子に応じた処理をする
                double result = operators.get(token).getOperator().process(numberStack);
                numberStack.offerFirst(String.valueOf(result));
            } catch (NullPointerException e) {
                throw new OperatorUndefinedExeption(token);
            }
        }

        return Double.parseDouble(numberStack.pollFirst());
    }
}
