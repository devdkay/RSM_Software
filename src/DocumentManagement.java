import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DocumentManagement {
    public static void manageDocuments(Scanner scanner, String role) {
        if ("owner".equalsIgnoreCase(role)) {
            System.out.println("Manage Documents:");
            System.out.println("1. Upload Document");
            System.out.println("2. Delete Document");
            System.out.print("Enter your choice: ");

            int documentManagementChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (documentManagementChoice) {
                case 1:
                    uploadDocument(scanner, role, role);
                    break;
                case 2:
                    deleteDocument(scanner, role, role);
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
            scanner.nextLine(); 

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

    private static void uploadDocument(Scanner scanner, String username, String role) {
        try {
            if ("owner".equalsIgnoreCase(role)) {
            System.out.print("Enter the document name: ");
            String documentName = scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(documentName + ".txt"))) {
                System.out.print("Enter the document content: ");
                String content = scanner.nextLine();
                writer.write(content);
                System.out.println("Document uploaded successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You don't have permission to upload documents.");
        }
            System.out.println("Document uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteDocument(Scanner scanner, String username, String role) {
        try {
            if ("owner".equalsIgnoreCase(role)) {
            System.out.print("Enter the document name to delete: ");
            String documentName = scanner.nextLine();
            File file = new File(documentName + ".txt");

            if (file.delete()) {
                System.out.println("Document deleted successfully!");
            } else {
                System.out.println("Failed to delete the document. It may not exist.");
            }
        } else {
            System.out.println("You don't have permission to delete documents.");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readDocument(Scanner scanner) {
        System.out.print("Enter the document name to read: ");
        String documentName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(documentName + ".txt"))) {
            String line;
            System.out.println("Document content for " + documentName + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the document: " + e.getMessage());
        }
    }
}
