import java.util.Deque;

public class Sin implements Operator {
    public void process(Deque<String> numberStack) {
        double angleDegree = Double.parseDouble(numberStack.pollFirst());
        double angleRadian = angleDegree * Math.PI / 180;
        double result = Math.sin(angleRadian);
        numberStack.offerFirst(String.valueOf(result));
    }
}
