public class Sin implements Operator {
    public double process(Double[] operands) {
        double angleDegree = operands[0];
        double angleRadian = angleDegree * Math.PI / 180;
        return Math.sin(angleRadian);
    }
}
