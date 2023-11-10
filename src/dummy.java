import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dummy {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                        String role = Roles.extractRole(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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

