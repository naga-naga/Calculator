public class Multiply extends Operator {
    public Multiply(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        return operands[0] * operands[1];
    }
}
