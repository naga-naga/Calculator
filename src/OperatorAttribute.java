public class OperatorAttribute {
    private int priority;
    private Operator operator;

    public OperatorAttribute(int priority, Operator operator) {
        this.priority = priority;
        this.operator = operator;
    }

    public int getPriority() {
        return this.priority;
    }

    public Operator getOperator() {
        return this.operator;
    }
}
