import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.NoSuchElementException;

public class Calculator {
    /**
     * 演算子を定義した Map
     */
    private Map<String, OperatorAttribute> operators;

    public Calculator(Map<String, OperatorAttribute> operators) {
        this.operators = operators;
    }

    /**
     * 逆ポーランド記法のキューをもとに計算を実行し，結果を返す
     * @param reversePolishQueue 逆ポーランド記法のキュー
     * @return 計算結果
     * @throws OperatorUndefinedExeption 演算子として定義されていないトークンが渡された場合
     * @throws InvalidExpressionException 計算式が不正な場合
     */
    public double calculate(Deque<String> reversePolishQueue) throws OperatorUndefinedExeption, InvalidExpressionException {
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
                int numberOfOperands = this.operators.get(token).getNumberOfOperands();
                for(int i = 0; i < numberOfOperands; i++){
                    // 左側のオペランドが Deque の左側に来るようにする
                    operands.offerFirst(Double.parseDouble(numberStack.removeFirst()));
                }

                double result = this.operators.get(token).getOperator().process(operands.toArray(new Double[numberOfOperands]));
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
