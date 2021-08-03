import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO: ここの Map は Calculator に入れる
        // 演算子の定義
        Map<String, Operator> operators = new HashMap<>();
        operators.put("+", new Add(10, 2));
        operators.put("-", new Subtract(10, 2));
        operators.put("*", new Multiply(20, 2));
        operators.put("/", new Divide(20, 2));
        operators.put("sin", new Sin(30, 1));

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
