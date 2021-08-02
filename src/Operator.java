public interface Operator {
    /**
     * 各演算子が行う具体的な処理
     * @param operands オペランドの配列．オペランドが2つある場合，0番目の要素が左側のオペランド．
     * @return 計算結果
     */
    public double process(Double[] operands);
}
