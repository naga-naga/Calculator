public class Tan extends Operator {
    public Tan(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        double angleDegree = operands[0];
        double angleRadian = angleDegree * Math.PI / 180;
        return Math.tan(angleRadian);
    }
}
