import java.io.*;
import java.util.Scanner;

public class DocumentManagement {
    public static void uploadDocument(Scanner scanner, String username, String role) {
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
    }

    public static void deleteDocument(Scanner scanner, String username, String role) {
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
    }

    public static void readDocument(Scanner scanner, String username, String role) {
        System.out.print("Enter the document name to read: ");
        String documentName = scanner.nextLine();
        File file = new File(documentName + ".txt");

        if (file.exists() && file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                System.out.println("Document content:");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Document not found or it is not a file.");
        }
    }
}
