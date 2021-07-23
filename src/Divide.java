import java.util.Deque;

public class Divide implements Operator {
    public double process(Deque<String> numberStack) {
        double right = Double.parseDouble(numberStack.pollFirst());
        double left = Double.parseDouble(numberStack.pollFirst());
        return left / right;
    }
}
