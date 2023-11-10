import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONObject;

public class TenantManagement {
    public static void addTenant(Scanner scanner) {
        System.out.print("Enter the new tenant's username: ");
        String newTenantUsername = scanner.nextLine();

        // Check if the username already exists
        if (!UserAuthentication.userExists(newTenantUsername)) {
            UserAuthentication.registerUser(scanner); 
            System.out.println("Tenant added successfully!");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    public static void viewTenants() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            System.out.println("List of Tenants:");
            while ((line = reader.readLine()) != null) {
                JSONObject userJson = new JSONObject(line);
                String role = userJson.getString("role");

                if ("tenant".equalsIgnoreCase(role)) {
                    String tenantUsername = userJson.getString("username");
                    System.out.println("Tenant: " + tenantUsername);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
