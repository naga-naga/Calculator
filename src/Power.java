public class Power extends Operator {
    public Power(int priority, int numberOfOperands) {
        super(priority, numberOfOperands);
    }

    public double process(Double[] operands) {
        return Math.pow(operands[0], operands[1]);
    }
}
