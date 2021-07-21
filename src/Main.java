import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // define operator
        Map<String, Integer> priority = new HashMap<>();
        priority.put("+", 10);
        priority.put("-", 10);
        priority.put("*", 20);
        priority.put("/", 20);

        // input
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        // parse
        Parser parser = new Parser();
        List<String> token = parser.tokenize(inputString);
        System.out.println(token);

        Deque<String> polish = parser.parse(inputString, priority);
        System.out.println(polish);

        // calculate
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate(inputString, priority));

        scanner.close();
    }
}
