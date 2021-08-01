import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.NoSuchElementException;

public class Calculator {
    public double calculate(Deque<String> reversePolishQueue, Map<String, OperatorAttribute> operators) throws OperatorUndefinedExeption, InvalidExpressionException {
        Deque<String> numberStack = new ArrayDeque<>();

        while (!reversePolishQueue.isEmpty()) {
            String token = reversePolishQueue.pollFirst();
            Deque<Double> operands = new ArrayDeque<>();

            // 数字の場合スタックに積む
            if (token.matches("\\d+(\\.\\d+)?")) {
                numberStack.offerFirst(token);
                continue;
            }

            // そうでない場合，演算子に応じた処理をする
            try {
                // 計算に必要な分だけスタックからオペランドを取り出す
                int numberOfOperands = operators.get(token).getNumberOfOperands();
                for(int i = 0; i < numberOfOperands; i++){
                    // 左側のオペランドが Deque の左側に来るようにする
                    operands.offerFirst(Double.parseDouble(numberStack.removeFirst()));
                }

                double result = operators.get(token).getOperator().process(operands.toArray(new Double[numberOfOperands]));
                numberStack.offerFirst(String.valueOf(result));
            } catch (NullPointerException e) {
                throw new OperatorUndefinedExeption(token);
            } catch (NoSuchElementException e) {
                throw new InvalidExpressionException();
            }
        }

        return Double.parseDouble(numberStack.pollFirst());
    }
}
