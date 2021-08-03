public class Add extends Operator {
    public Add(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        return operands[0] + operands[1];
    }
}
