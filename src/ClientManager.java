import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    
    public void addClient(String name, String email, String phone) throws SQLException {
        String query = "INSERT INTO clients (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
        }
    }

    public List<String> viewClients() throws SQLException {
        List<String> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clients.add(rs.getInt("id") + ": " + rs.getString("name"));
            }
        }
        return clients;
    }

    public void updateClient(int id, String name, String email, String phone) throws SQLException {
        String query = "UPDATE clients SET name = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No client found with ID: " + id);
            } else {
                System.out.println("Client updated successfully.");
            }
        }
    }

    public void deleteClient(int id) throws SQLException {
        String query = "DELETE FROM clients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No client found with ID: " + id);
            } else {
                System.out.println("Client deleted successfully.");
            }
        }
    }
}