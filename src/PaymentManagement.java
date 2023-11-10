import java.util.Scanner;

public class PaymentManagement {
    public static void makePayment(Scanner scanner, String username, String role) {
        if ("tenant".equalsIgnoreCase(role)) {
            System.out.print("Enter the amount of rent to pay: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive value.");
                return;
            }
            
            System.out.println("Select a payment method:");
            System.out.println("1. Apple Pay");
            System.out.println("2. PayPal");
            System.out.println("3. Google Pay");
            System.out.println("4. Credit/Debit Card");
            System.out.print("Enter your choice: ");

            int paymentMethodChoice = scanner.nextInt();
            scanner.nextLine(); 

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
        // logic for Apple Pay
        notifyOwner("tenant", 345);
    }

    private static void makePayPalPayment() {
        System.out.println("Processing PayPal payment...");
        // logic for PayPal
        notifyOwner("tenant", 345);
    }

    private static void makeGooglePayPayment() {
        System.out.println("Processing Google Pay payment...");
        // logic for Google Pay
        notifyOwner("tenant", 345);
    }

    private static void makeCardPayment(Scanner scanner) {
        System.out.print("Enter your card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter the expiration date (MM/YYYY): ");
        String expirationDate = scanner.nextLine();

        System.out.print("Enter the CVV: ");
        String cvv = scanner.nextLine();

        System.out.println("Processing Credit/Debit Card payment...");
        // logic for Credit/Debit Card payment
        notifyOwner("tenant", 345);
    }

    private static void notifyOwner(String tenantUsername, double amount) {
        // notification logic (sending an email to the owner)
        System.out.println("Notification sent to owner: Tenant " + tenantUsername +
                " has paid rent of $" + amount);
    }
}
