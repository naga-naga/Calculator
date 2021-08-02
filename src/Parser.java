import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Parser {
    /**
     * 与えられた文字列をトークン化して返す
     * @param str 計算式の文字列
     * @return トークンを要素とするキュー
     */
    public Deque<String> tokenize(String str) {
        // 記号の前後にスペースを入れ，連続するスペースを一つにし，始めのスペースを消し，分割する
        String[] tokens = str.replaceAll("([^a-zA-Z0-9.])", " $1 ")
                .replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .split("\\s");
        Deque<String> retDeque = new ArrayDeque<>();

        for (String token : tokens) {
            retDeque.offerLast(token);
        }
        retDeque.offerLast("END"); // 終わりを示す END トークンを追加しておく
        return retDeque;
    }

    /**
     * トークン列を構文解析し，逆ポーランド記法のキューを返す
     * @param tokensQueue トークン列のキュー
     * @param operators 演算子を定義した Map
     * @return 逆ポーランド記法のキュー
     * @throws OperatorUndefinedExeption 演算子として定義されていないトークンが渡された場合
     */
    public Deque<String> parse(Deque<String> tokensQueue, Map<String, OperatorAttribute> operators) throws OperatorUndefinedExeption {
        Deque<String> reversePolishQueue = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        while (!tokensQueue.isEmpty()) {
            String token = tokensQueue.pollFirst();

            // 最後のトークンの場合
            if (token.matches("END")) {
                // 演算子スタックに残っている演算子をキューに出す
                while (!operatorStack.isEmpty()) {
                    reversePolishQueue.offerLast(operatorStack.pollFirst());
                }
            }

            // トークンが数字の場合
            if (token.matches("\\d+(\\.\\d+)?")) {
                // キューにそのまま入れる
                reversePolishQueue.offerLast(token);
                continue;
            }

            // トークンが開き括弧の場合
            if (token.matches("\\(")) {
                // 括弧内を再帰的にパースする
                Deque<String> partialReversePolishQueue = parse(tokensQueue, operators);
                while (!partialReversePolishQueue.isEmpty()) {
                    reversePolishQueue.offerLast(partialReversePolishQueue.pollFirst());
                }
                continue;
            }

            // トークンが閉じ括弧の場合
            if (token.matches("\\)")) {
                // 演算子スタックに残っている演算子をキューに出す
                while (!operatorStack.isEmpty()) {
                    reversePolishQueue.offerLast(operatorStack.pollFirst());
                }
                return reversePolishQueue;
            }

            // トークンが演算子の場合
            // 演算子スタックが空ならスタックに積む
            if (operatorStack.isEmpty()) {
                operatorStack.offerFirst(token);
                continue;
            }

            // 演算子スタックが空でない場合
            // 現在のトークンがスタックの演算子より優先度が高いならスタックに積む
            try {
                if (operators.get(token).getPriority() > operators.get(operatorStack.peekFirst()).getPriority()) {
                    operatorStack.offerFirst(token);
                } else { // そうでない場合はスタックの演算子をキューに出し，現在のトークンをスタックに積む
                    reversePolishQueue.offerLast(operatorStack.pollFirst());
                    operatorStack.offerFirst(token);
                }
            } catch (NullPointerException e) {
                throw new OperatorUndefinedExeption(token);
            }
        }

        return reversePolishQueue;
    }
}
