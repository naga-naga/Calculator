public class Subtract extends Operator {
    public Subtract(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        return operands[0] - operands[1];
    }
}
