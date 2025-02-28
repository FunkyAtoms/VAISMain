import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class ServiceManager {

    public void addService(String description, double rate) throws SQLException {
        String query = "INSERT INTO services (description, rate) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, description);
            stmt.setDouble(2, rate);
            stmt.executeUpdate();
        }
    }

    public List<String> viewServices() throws SQLException {
        List<String> services = new ArrayList<>();
        String query = "SELECT * FROM services";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                services.add(rs.getInt("id") + ": " + rs.getString("description") + " - $" + rs.getDouble("rate"));
            }
        }
        return services;
    }

    public void updateService(int id, String description, double rate) throws SQLException {
        String query = "UPDATE services SET description = ?, rate = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, description);
            stmt.setDouble(2, rate);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No service found with ID: " + id);
            } else {
                System.out.println("Service updated successfully.");
            }
        }
    }

    public void deleteService(int id) throws SQLException {
        String query = "DELETE FROM services WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No service found with ID: " + id);
            } else {
                System.out.println("Service deleted successfully.");
            }
        }
    }
}
