public class Sin extends Operator {
    public Sin(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        double angleDegree = operands[0];
        double angleRadian = angleDegree * Math.PI / 180;
        return Math.sin(angleRadian);
    }
}
