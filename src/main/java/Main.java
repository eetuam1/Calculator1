import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator(); // Create an instance of the Calculator
        boolean running = true; // Control variable for the loop

        // Main application loop
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add two numbers");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt(); // Get user's choice
                scanner.nextLine(); // Consume newline character

                // Handle user's choice
                switch (choice) {
                    case 1:
                        System.out.print("Enter the first number: ");
                        int num1 = scanner.nextInt();
                        System.out.print("Enter the second number: ");
                        int num2 = scanner.nextInt();

                        int sum = calculator.addNumbers(num1, num2); // Call the method to add numbers
                        System.out.println("Here is the sum: " + sum);
                        break;
                    case 2:
                        running = false; // Exit the loop
                        break;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close(); // Close the scanner resource
    }
}
