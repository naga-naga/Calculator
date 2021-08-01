import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // define operator
        Map<String, OperatorAttribute> operators = new HashMap<>();
        operators.put("+", new OperatorAttribute(10, new Add()));
        operators.put("-", new OperatorAttribute(10, new Subtract()));
        operators.put("*", new OperatorAttribute(20, new Multiply()));
        operators.put("/", new OperatorAttribute(20, new Divide()));
        operators.put("sin", new OperatorAttribute(30, new Sin()));

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Calculator calculator = new Calculator();

        System.out.println("終了する場合は exit と入力してください");

        while (true) {
            System.out.print(">>> ");
            String inputString = scanner.nextLine();

            if (inputString.equals("exit")) {
                break;
            }

            Deque<String> tokens = parser.tokenize(inputString);

            try {
                Deque<String> polish = parser.parse(tokens, operators);
                System.out.println(calculator.calculate(polish, operators));
            } catch (OperatorUndefinedExeption e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        scanner.close();
    }
}
