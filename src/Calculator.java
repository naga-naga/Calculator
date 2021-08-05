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
     */
    public double calculate(String fomulaString) {
        Parser parser = new Parser(operators);
        Deque<Token> tokensQueue = parser.tokenize(fomulaString);
        Deque<Token> reversePolishQueue = parser.parse(tokensQueue);

        Deque<Token> numberStack = new ArrayDeque<>();
        double calculationResult;

        while (!reversePolishQueue.isEmpty()) {
            Token token = reversePolishQueue.pollFirst();
            TokenType tokenType = token.getTokenType();
            Deque<Double> operands = new ArrayDeque<>();

            if (tokenType == TokenType.NUMBER) { // 数字の場合スタックに積む
                numberStack.offerFirst(token);
            } else if (tokenType == TokenType.OPERATOR) { // 演算子の場合，それぞれの処理をする
                // 計算に必要な分だけスタックからオペランドを取り出す
                int numberOfOperands = this.operators.get(token.getText()).getNumberOfOperands();
                for (int i = 0; i < numberOfOperands; i++) {
                    try {
                        // 左側のオペランドが Deque の左側に来るようにする
                        operands.offerFirst(Double.parseDouble(numberStack.removeFirst().getText()));
                    } catch (NoSuchElementException e) {
                        System.err.println("不正な式です");
                        return 0.0;
                    }
                }

                double processingResult = this.operators.get(token.getText()).process(operands.toArray(new Double[numberOfOperands]));
                numberStack.offerFirst(new Token(TokenType.NUMBER, String.valueOf(processingResult)));
            } else { // この状態にはならないはず
                System.err.println("エラー");
                return 0.0; // とりあえず 0.0 を返す
            }
        }

        try {
            calculationResult = Double.parseDouble(numberStack.removeFirst().getText());
        } catch (NoSuchElementException e) {
            System.err.println("不正な式です");
            return 0.0;
        }

        return calculationResult;
    }
}
