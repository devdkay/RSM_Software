import java.util.Scanner;

public class PaymentManagement {
    public static void makePayment(Scanner scanner, String username, String role) {
        if ("tenant".equalsIgnoreCase(role)) {
            System.out.println("Select a payment method:");
            System.out.println("1. Apple Pay");
            System.out.println("2. PayPal");
            System.out.println("3. Google Pay");
            System.out.println("4. Credit/Debit Card");
            System.out.print("Enter your choice: ");

            int paymentMethodChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (paymentMethodChoice) {
                case 1:
                    makeApplePayPayment();
                    break;
                case 2:
                    makePayPalPayment();
                    break;
                case 3:
                    makeGooglePayPayment();
                    break;
                case 4:
                    makeCardPayment(scanner);
                    break;
                default:
                    System.out.println("Invalid payment method choice.");
                    return;
            }
        } else {
            System.out.println("Only tenants can make payments.");
        }
    }

    private static void makeApplePayPayment() {
        System.out.println("Processing Apple Pay payment...");
        // Add logic for Apple Pay
    }

    private static void makePayPalPayment() {
        System.out.println("Processing PayPal payment...");
        // Add logic for PayPal
    }

    private static void makeGooglePayPayment() {
        System.out.println("Processing Google Pay payment...");
        // Add logic for Google Pay
    }

    private static void makeCardPayment(Scanner scanner) {
        System.out.print("Enter your card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter the expiration date (MM/YYYY): ");
        String expirationDate = scanner.nextLine();

        System.out.print("Enter the CVV: ");
        String cvv = scanner.nextLine();

        System.out.println("Processing Credit/Debit Card payment...");
        // Add logic for Credit/Debit Card payment
    }

    private static void notifyOwner(String tenantUsername, double amount) {
        // Replace this with actual notification logic (e.g., sending an email to the owner)
        System.out.println("Notification sent to owner: Tenant " + tenantUsername +
                " has paid rent of $" + amount);
    }
}
