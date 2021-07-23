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

        // input
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        // parse
        Parser parser = new Parser();
        Deque<String> tokens = parser.tokenize(inputString);
        System.out.println(tokens);

        Deque<String> polish = parser.parse(tokens, operators);
        System.out.println(polish);

        // calculate
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate(polish, operators));

        scanner.close();
    }
}
