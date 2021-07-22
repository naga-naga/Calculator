import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Parser {
    public Deque<String> tokenize(String str) {
        // 記号の前後にスペースを入れ，連続するスペースを一つにし，分割する
        String[] tokens = str.replaceAll("([^a-zA-Z0-9.])", " $1 ").replaceAll("\\s+", " ").split("\\s");
        Deque<String> retDeque = new ArrayDeque<>();
        for (String token : tokens) {
            retDeque.offerLast(token);
        }
        retDeque.offerLast("END"); // 終わりを示す END トークンを追加しておく
        return retDeque;
    }

    public Deque<String> parse(Deque<String> tokens, Map<String, Integer> priority) {
        Deque<String> reversePolishQueue = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (String token : tokens) {
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

            // トークンが演算子の場合
            // 演算子スタックが空ならスタックに積む
            if (operatorStack.isEmpty()) {
                operatorStack.offerFirst(token);
                continue;
            }

            // 演算子スタックが空でない場合
            // 現在のトークンがスタックの演算子より優先度が高いならスタックに積む
            if (priority.get(token) > priority.get(operatorStack.peekFirst())) {
                operatorStack.offerFirst(token);
            } else { // そうでない場合はスタックの演算子をキューに出し，現在のトークンをスタックに積む
                reversePolishQueue.offerLast(operatorStack.pollFirst());
                operatorStack.offerFirst(token);
            }
        }

        return reversePolishQueue;
    }
}
