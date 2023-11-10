import java.util.Scanner;

public class Roles {
    public static void handleOwnerOptions(Scanner scanner) {
        int ownerChoice;
        do {
            System.out.println("Options for Owner:");
            System.out.println("1. Manage Documents");
            System.out.println("2. Add Tenant");
            System.out.println("3. View Tenants");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            ownerChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (ownerChoice) {
                case 1:
                    DocumentManagement.manageDocuments(scanner, "owner");
                    break;
                case 2:
                    TenantManagement.addTenant(scanner);
                    break;
                case 3:
                    TenantManagement.viewTenants();
                    break;
                case 4:
                    System.out.println("Exiting owner options.");
                    break;
                default:
                    System.out.println("Invalid owner choice.");
            }
        } while (ownerChoice != 4);
    }

    public static void handleTenantOptions(Scanner scanner, String username) {
        int tenantChoice;
        do {
            System.out.println("Options for Tenant:");
            System.out.println("1. View Documents");
            System.out.println("2. Make Payment");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            tenantChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (tenantChoice) {
                case 1:
                    DocumentManagement.readDocument(scanner);
                    break;
                case 2:
                    PaymentManagement.makePayment(scanner, username, "tenant");
                    break;
                case 3:
                    System.out.println("Exiting tenant options.");
                    break;
                default:
                    System.out.println("Invalid tenant choice.");
            }
        } while (tenantChoice != 3);
    }

    public static String extractRole(String line) {
        String[] parts = line.split(":");
        if (parts.length == 3 && parts[0].trim().equals("role")) {
            return parts[3].trim();
        } else {
            return "Unknown";
        }
    }
}

