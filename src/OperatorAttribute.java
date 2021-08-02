public class OperatorAttribute {
    /**
     * 演算子の優先度．値が大きいほど優先して計算される．
     */
    private int priority;

    /**
     * Operator インタフェースを実装したクラス
     */
    private Operator operator;

    /**
     * オペランドの数
     */
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
