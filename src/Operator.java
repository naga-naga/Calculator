public class Operator implements Calculatable {
    /**
     * 演算子の優先度．値が大きいほど優先して計算される．
     */
    private int priority;

    /**
     * オペランドの数
     */
    private int numberOfOperands;

    public Operator(int priority, int numberOfOperands) {
        this.priority = priority;
        this.numberOfOperands = numberOfOperands;
    }

    public double process(Double[] args) {
        return 0.0;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getNumberOfOperands() {
        return this.numberOfOperands;
    }
}
