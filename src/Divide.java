public class Divide extends Operator {
    public Divide(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        return operands[0] / operands[1];
    }
}
