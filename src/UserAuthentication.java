import java.io.*;
import java.util.Scanner;

public class UserAuthentication {
    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(" ");
                if (userInfo.length == 3) {
                    String storedUsername = userInfo[0];
                    if (username.equals(storedUsername)) {
                        return true;
                    }
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
                writer.write(username + " " + password + " " + role);
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
                    String[] userInfo = line.split(" ");
                    if (userInfo.length == 3) {
                        String storedUsername = userInfo[0];
                        String storedPassword = userInfo[1];
                        String role = userInfo[2];

                        if (username.equals(storedUsername) && password.equals(storedPassword)) {
                            System.out.println("Welcome, " + username + "! Your role is: " + role);

                            DocumentManagement.manageDocuments(scanner, role);

                            if ("tenant".equalsIgnoreCase(role)) {
                                PaymentManagement.makePayment(scanner, username, role);
                            }
                            return true;
                        }

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