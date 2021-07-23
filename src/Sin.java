import java.util.Deque;

public class Sin implements Operator {
    public double process(Deque<String> numberStack) {
        double angleDegree = Double.parseDouble(numberStack.pollFirst());
        double angleRadian = angleDegree * Math.PI / 180;
        return Math.sin(angleRadian);
    }
}
