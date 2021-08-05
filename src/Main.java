import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("終了する場合は exit と入力してください");

        while (true) {
            System.out.print(">>> ");
            String inputString = scanner.nextLine();

            if (inputString.equals("exit")) {
                break;
            }

            System.out.println(calculator.calculate(inputString));
        }

        scanner.close();
    }
}
