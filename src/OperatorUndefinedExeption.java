public class OperatorUndefinedExeption extends Exception {
    OperatorUndefinedExeption(String operator) {
        super("演算子 " + operator + " は定義されていません");
    }
}
