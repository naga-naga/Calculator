import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 演算子の定義
        Map<String, OperatorAttribute> operators = new HashMap<>();
        operators.put("+", new OperatorAttribute(10, new Add(), 2));
        operators.put("-", new OperatorAttribute(10, new Subtract(), 2));
        operators.put("*", new OperatorAttribute(20, new Multiply(), 2));
        operators.put("/", new OperatorAttribute(20, new Divide(), 2));
        operators.put("sin", new OperatorAttribute(30, new Sin(), 1));

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(operators);
        Calculator calculator = new Calculator(operators);

        System.out.println("終了する場合は exit と入力してください");

        while (true) {
            System.out.print(">>> ");
            String inputString = scanner.nextLine();

            if (inputString.equals("exit")) {
                break;
            }

            Deque<String> tokens = parser.tokenize(inputString);

            try {
                Deque<String> polish = parser.parse(tokens);
                System.out.println(calculator.calculate(polish));
            } catch (OperatorUndefinedExeption | InvalidExpressionException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        scanner.close();
    }
}
