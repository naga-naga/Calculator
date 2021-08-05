import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Calculator {
    /**
     * 演算子を定義した Map
     */
    private Map<String, Operator> operators;

    {
        operators = new HashMap<>();
        operators.put("+", new Add(10, 2));
        operators.put("-", new Subtract(10, 2));
        operators.put("*", new Multiply(20, 2));
        operators.put("/", new Divide(20, 2));
        operators.put("sin", new Sin(30, 1));
    }

    /**
     * 計算式の文字列をもとに計算を実行し，結果を返す
     *
     * @param fomulaString 計算式の文字列
     * @return 計算結果
     * @throws OperatorUndefinedExeption  演算子として定義されていないトークンが渡された場合
     * @throws InvalidExpressionException 計算式が不正な場合
     */
    public double calculate(String fomulaString) throws OperatorUndefinedExeption, InvalidExpressionException {
        Parser parser = new Parser(operators);
        Deque<Token> tokensQueue = parser.tokenize(fomulaString);
        Deque<String> reversePolishQueue = parser.parse(tokensQueue);

        Deque<String> numberStack = new ArrayDeque<>();
        double calculationResult;

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
                // TODO: nullチェック．try-catch をなるべく減らす
                // 計算に必要な分だけスタックからオペランドを取り出す
                int numberOfOperands = this.operators.get(token).getNumberOfOperands();
                for (int i = 0; i < numberOfOperands; i++) {
                    // 左側のオペランドが Deque の左側に来るようにする
                    operands.offerFirst(Double.parseDouble(numberStack.removeFirst()));
                }

                double processingResult = this.operators.get(token).process(operands.toArray(new Double[numberOfOperands]));
                numberStack.offerFirst(String.valueOf(processingResult));
            } catch (NullPointerException e) {
                throw new OperatorUndefinedExeption(token);
            } catch (NoSuchElementException e) {
                throw new InvalidExpressionException();
            }
        }

        try {
            calculationResult = Double.parseDouble(numberStack.removeFirst());
        } catch (NoSuchElementException e) {
            throw new InvalidExpressionException();
        }

        return calculationResult;
    }
}
