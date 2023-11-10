import java.io.*;
import java.util.Scanner;

public class UserAuthentication {
    private static final String FILE_PATH = "users.txt";

    public static boolean userExists(String username) {
        ensureFileExists();  // Ensure the file exists before reading

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"username\":\"" + username + "\"")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerUser(Scanner scanner) {
        ensureFileExists();  // Ensure the file exists before writing

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (!userExists(username)) {
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your role (owner/tenant): ");
            String role = scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                String userData = String.format("{\"username\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}", username, password, role);
                writer.write(userData);
                writer.newLine();
                System.out.println("User registered successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }
    

    public static boolean login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (userExists(username)) {
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("\"username\":\"" + username + "\"") && line.contains("\"password\":\"" + password + "\"")) {
                        String role = extractRole(line);
                        System.out.println("Welcome, " + username + "! Your role is: " + role);

                        if ("owner".equalsIgnoreCase(role)) {
                            Roles.handleOwnerOptions(scanner);
                        } else if ("tenant".equalsIgnoreCase(role)) {
                            Roles.handleTenantOptions(scanner, username);
                        }
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Invalid username or password.");
        } else {
            System.out.println("User not found. Please register.");
        }
        return false;
    }

    private static String extractRole(String jsonLine) {
        int roleStart = jsonLine.indexOf("\"role\":\"") + 8;
        int roleEnd = jsonLine.indexOf("\"", roleStart);
        return jsonLine.substring(roleStart, roleEnd);
    }
}
