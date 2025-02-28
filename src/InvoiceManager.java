import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManager {

    // Method to create a new invoice for a client
    public void createInvoice(int clientId) throws SQLException {
        String query = "INSERT INTO invoices (client_id, date) VALUES (?, NOW())";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            stmt.executeUpdate();
            System.out.println("Invoice created successfully for client ID: " + clientId);
        }
    }

    // Method to add a service to an existing invoice
    public void addServiceToInvoice(int invoiceId, int serviceId, int quantity) throws SQLException {
        String query = "INSERT INTO invoice_items (invoice_id, service_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, serviceId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
            System.out.println("Service added to invoice successfully.");
        }
    }

    // Method to view all invoices along with their items
    public List<String> viewInvoices() throws SQLException {
        List<String> invoices = new ArrayList<>();
        String query = "SELECT i.id AS invoice_id, c.name AS client_name, i.date " +
                       "FROM invoices i JOIN clients c ON i.client_id = c.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                invoices.add("Invoice ID: " + rs.getInt("invoice_id") + 
                              ", Client: " + rs.getString("client_name") + 
                              ", Date: " + rs.getDate("date"));
            }
        }
        return invoices;
    }

    // Method to view items in a specific invoice
    public List<String> viewInvoiceItems(int invoiceId) throws SQLException {
        List<String> items = new ArrayList<>();
        String query = "SELECT ii.quantity, s.description, s.rate " +
                       "FROM invoice_items ii JOIN services s ON ii.service_id = s.id " +
                       "WHERE ii.invoice_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add("Quantity: " + rs.getInt("quantity") + 
                           ", Service: " + rs.getString("description") + 
                           ", Rate: $" + rs.getDouble("rate"));
            }
        }
        return items;
    }
}
