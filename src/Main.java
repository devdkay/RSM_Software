/*
 *  COMP 2663 Assignment 4 
 *  
 *  Project Team Members:
    *  Manav Patel (0301184) (33.3%)
    *  Shishya Agrawal (0264007) (33.3%)
    *  Dev Kumar (0263897) (33.3%)
 * 
 */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to User Authentication System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                UserAuthentication.login(scanner);
            } else if (choice == 2) {
                UserAuthentication.registerUser(scanner);
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}