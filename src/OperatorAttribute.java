public class OperatorAttribute {
    private int priority;
    private Operator operator;
    private int numberOfOperands;

    public OperatorAttribute(int priority, Operator operator, int numberOfOperands) {
        this.priority = priority;
        this.operator = operator;
        this.numberOfOperands = numberOfOperands;
    }

    public int getPriority() {
        return this.priority;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public int getNumberOfOperands() {
        return this.numberOfOperands;
    }
}
