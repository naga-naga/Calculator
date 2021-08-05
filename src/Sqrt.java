public class Sqrt extends Operator {
    public Sqrt(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        return Math.sqrt(operands[0]);
    }
}
