import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();  // Create an instance of Calculator

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int num1 = sc.nextInt();

        System.out.println("Enter the second number: ");
        int num2 = sc.nextInt();

        int sum = calculator.addNumbers(num1, num2);  // Call the addNumbers method
        System.out.println("Here is the sum: " + sum);

        sc.close();  // Closing the scanner to avoid resource leaks
    }
}
