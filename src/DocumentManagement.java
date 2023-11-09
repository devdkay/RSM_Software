import java.util.Scanner;

public class DocumentManagement {
    public static void manageDocuments(Scanner scanner, String role) {
        if ("owner".equalsIgnoreCase(role)) {
            System.out.println("Manage Documents:");
            System.out.println("1. Upload Document");
            System.out.println("2. Delete Document");
            System.out.print("Enter your choice: ");

            int documentManagementChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (documentManagementChoice) {
                case 1:
                    uploadDocument(scanner);
                    break;
                case 2:
                    deleteDocument(scanner);
                    break;
                default:
                    System.out.println("Invalid document management choice.");
            }
        } else if ("tenant".equalsIgnoreCase(role)) {
            System.out.println("Options for Tenant:");
            System.out.println("1. View Documents");
            System.out.println("2. Make Payment");
            System.out.print("Enter your choice: ");

            int tenantChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (tenantChoice) {
                case 1:
                    readDocument(scanner);
                    break;
                case 2:
                    PaymentManagement.makePayment(scanner, null, role);
                    break;
                default:
                    System.out.println("Invalid tenant choice.");
            }
        } else {
            System.out.println("Invalid user role.");
        }
    }

    private static void uploadDocument(Scanner scanner) {
        System.out.print("Enter the document name: ");
        String documentName = scanner.nextLine();

        try {
            // Your logic for uploading a document
            System.out.println("Document uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteDocument(Scanner scanner) {
        System.out.print("Enter the document name to delete: ");
        String documentName = scanner.nextLine();

        try {
            // Your logic for deleting a document
            System.out.println("Document deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readDocument(Scanner scanner) {
        System.out.print("Enter the document name to read: ");
        String documentName = scanner.nextLine();

        try {
            // Your logic for reading a document
            System.out.println("Document content:");
            System.out.println("Lease terms and conditions...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
