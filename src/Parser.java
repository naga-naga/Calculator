import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Parser {
    /**
     * 演算子を定義した Map
     */
    private Map<String, Operator> operators;

    public Parser(Map<String, Operator> operators) {
        this.operators = operators;
    }

    /**
     * 与えられた文字列をトークン化して返す
     *
     * @param str 計算式の文字列
     * @return トークンを要素とするキュー
     */
    public Deque<Token> tokenize(String str) {
        // 記号の前後にスペースを入れ，連続するスペースを一つにし，始めのスペースを消し，分割する
        String[] tokens = str.replaceAll("([^a-zA-Z0-9.])", " $1 ")
                .replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .split("\\s");
        Deque<Token> retDeque = new ArrayDeque<>();

        for (String tokenString : tokens) {
            Token token;
            if (tokenString.matches("\\d+(\\.\\d+)?")) { // 数字
                token = new Token(TokenType.NUMBER, tokenString);
            } else if (tokenString.matches("\\(")) { // 開き括弧
                token = new Token(TokenType.LEFT_PAREN, tokenString);
            } else if (tokenString.matches("\\)")) { // 閉じ括弧
                token = new Token(TokenType.RIGHT_PAREN, tokenString);
            } else if (operators.containsKey(tokenString)) { // 演算子
                token = new Token(TokenType.OPERATOR, tokenString);
            } else { // 未定義
                token = new Token(TokenType.UNDEFINED, tokenString);
            }
            retDeque.offerLast(token);
        }
        retDeque.offerLast(new Token(TokenType.END, "END")); // 終わりを示す END トークンを追加しておく
        return retDeque;
    }

    /**
     * トークン列を構文解析し，逆ポーランド記法のキューを返す
     *
     * @param tokensQueue トークン列のキュー
     * @return 逆ポーランド記法のキュー
     */
    public Deque<String> parse(Deque<Token> tokensQueue) {
        Deque<String> reversePolishQueue = new ArrayDeque<>();
        Deque<Token> operatorStack = new ArrayDeque<>();

        while (!tokensQueue.isEmpty()) {
            Token token = tokensQueue.pollFirst();
            TokenType tokenType = token.getTokenType();

            switch (tokenType) {
                case UNDEFINED:
                    System.out.println("演算子が未定義です");
                    // とりあえず 0.0 を返しておく
                    Deque<String> retDeque = new ArrayDeque<>();
                    retDeque.offerFirst("0.0");
                    return retDeque;
                case END:
                    // 最後のトークンの場合
                    // 演算子スタックに残っている演算子をキューに出す
                    while (!operatorStack.isEmpty()) {
                        reversePolishQueue.offerLast(operatorStack.pollFirst().getText());
                    }
                    break;
                case NUMBER:
                    // トークンが数字の場合
                    // キューにそのまま入れる
                    reversePolishQueue.offerLast(token.getText());
                    break;
                case LEFT_PAREN:
                    // トークンが開き括弧の場合
                    // 括弧内を再帰的にパースする
                    Deque<String> partialReversePolishQueue = parse(tokensQueue);
                    while (!partialReversePolishQueue.isEmpty()) {
                        reversePolishQueue.offerLast(partialReversePolishQueue.pollFirst());
                    }
                    break;
                case RIGHT_PAREN:
                    // トークンが閉じ括弧の場合
                    // 演算子スタックに残っている演算子をキューに出す
                    while (!operatorStack.isEmpty()) {
                        reversePolishQueue.offerLast(operatorStack.pollFirst().getText());
                    }
                    return reversePolishQueue;
                case OPERATOR:
                    // トークンが演算子の場合
                    // 演算子スタックが空ならスタックに積む
                    if (operatorStack.isEmpty()) {
                        operatorStack.offerFirst(token);
                        continue;
                    }

                    // 演算子スタックが空でない場合
                    // 現在のトークンがスタックの演算子より優先度が高いならスタックに積む
                    int currentPriority = this.operators.get(token.getText()).getPriority();
                    int stackPriority = this.operators.get(operatorStack.peekFirst().getText()).getPriority();

                    if (currentPriority > stackPriority) {
                        operatorStack.offerFirst(token);
                    } else { // 現在のトークンの演算子の優先度が低いか同じ場合，スタックの演算子をキューに出し，現在のトークンをスタックに積む
                        reversePolishQueue.offerLast(operatorStack.pollFirst().getText());
                        operatorStack.offerFirst(token);
                    }
                    break;
                default:
                    break;
            }
        }
        return reversePolishQueue;
    }
}
