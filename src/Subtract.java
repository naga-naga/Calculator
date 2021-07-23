import java.util.Deque;

public class Subtract implements Operator {
    public void process(Deque<String> numberStack) {
        double right = Double.parseDouble(numberStack.pollFirst());
        double left = Double.parseDouble(numberStack.pollFirst());
        double result = left - right;
        numberStack.offerFirst(String.valueOf(result));
    }
}
