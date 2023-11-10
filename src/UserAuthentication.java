import java.io.*;
import java.util.Scanner;

public class UserAuthentication {
    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("username") && line.contains(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerUser(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (!userExists(username)) {
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your role (owner/tenant): ");
            String role = scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write("[username: " + username + "\n password: " + password + "\n role: " + role + "],");
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
                    if (line.contains("password") && line.contains(password)) {
                        String role = Roles.extractRole(line);
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
}