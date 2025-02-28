import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ClientManager clientManager = new ClientManager();
    private static ServiceManager serviceManager = new ServiceManager();
    private static InvoiceManager invoiceManager = new InvoiceManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Invoice Management System");
            System.out.println("1. Client Management");
            System.out.println("2. Service Management");
            System.out.println("3. Invoice Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    clientManagementMenu();
                    break;
                case 2:
                    serviceManagementMenu();
                    break;
                case 3:
                    invoiceManagementMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void clientManagementMenu() {
        while (true) {
            System.out.println("Client Management");
            System.out.println("1. Add Client");
            System.out.println("2. View Clients");
            System.out.println("3. Update Client");
            System.out.println("4. Delete Client");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    addClient();
                    break;
                case 2:
                    viewClients();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void serviceManagementMenu() {
        while (true) {
            System.out.println("Service Management");
            System.out.println("1. Add Service");
            System.out.println("2. View Services");
            System.out.println("3. Update Service");
            System.out.println("4. Delete Service");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    addService();
                    break;
                case 2:
                    viewServices();
                    break;
                case 3:
                    updateService();
                    break;
                case 4:
                    deleteService();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void invoiceManagementMenu() {
        while (true) {
            System.out.println("Invoice Management");
            System.out.println("1. Create Invoice");
            System.out.println("2. Add Service to Invoice");
            System.out.println("3. View Invoices");
            System.out.println("4. View Invoice Items");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    createInvoice();
                    break;
                case 2:
                    addServiceToInvoice();
                    break;
                case 3:
                    viewInvoices();
                    break;
                case 4:
                    viewInvoiceItems();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client email: ");
        String email = scanner.nextLine();
        System.out.print("Enter client phone: ");
        String phone = scanner.nextLine();

        try {
            clientManager.addClient(name, email, phone);
            System.out.println("Client added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    private static void viewClients() {
        try {
            List<String> clients = clientManager.viewClients();
            for (String client : clients) {
                System.out.println(client);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing clients: " + e.getMessage());
        }
    }

    private static void updateClient() {
        System.out.print("Enter client ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter new client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new client email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new client phone: ");
        String phone = scanner.nextLine();

        try {
            clientManager.updateClient(id, name, email, phone);
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    private static void deleteClient() {
        System.out.print("Enter client ID: ");
        int id = scanner.nextInt();

        try {
            clientManager.deleteClient(id);
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }

    private static void addService() {
        System.out.print("Enter service description: ");
        String description = scanner.nextLine();
        System.out.print("Enter service rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        try {
            serviceManager.addService(description, rate);
            System.out.println("Service added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding service: " + e.getMessage());
        }
    }

    private static void viewServices() {
        try {
            List<String> services = serviceManager.viewServices();
            for (String service : services) {
                System.out.println(service);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing services: " + e.getMessage());
        }
    }

    private static void updateService() {
        System.out.print("Enter service ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter new service description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new service rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        try {
            serviceManager.updateService(id, description, rate);
        } catch (SQLException e) {
            System.out.println("Error updating service: " + e.getMessage());
        }
    }

    private static void deleteService() {
        System.out.print("Enter service ID: ");
        int id = scanner.nextInt();

        try {
            serviceManager.deleteService(id);
        } catch (SQLException e) {
            System.out.println("Error deleting service: " + e.getMessage());
        }
    }

    private static void createInvoice() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();

        try {
            invoiceManager.createInvoice(clientId);
        } catch (SQLException e) {
            System.out.println("Error creating invoice: " + e.getMessage());
        }
    }

    private static void addServiceToInvoice() {
        System.out.print("Enter invoice ID: ");
        int invoiceId = scanner.nextInt();
        System.out.print("Enter service ID: ");
        int serviceId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        try {
            invoiceManager.addServiceToInvoice(invoiceId, serviceId, quantity);
        } catch (SQLException e) {
            System.out.println("Error adding service to invoice: " + e.getMessage());
        }
    }

    private static void viewInvoices() {
        try {
            List<String> invoices = invoiceManager.viewInvoices();
            for (String invoice : invoices) {
                System.out.println(invoice);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing invoices: " + e.getMessage());
        }
    }

    private static void viewInvoiceItems() {
        System.out.print("Enter invoice ID: ");
        int invoiceId = scanner.nextInt();

        try {
            List<String> items = invoiceManager.viewInvoiceItems(invoiceId);
            for (String item : items) {
                System.out.println(item);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing invoice items: " + e.getMessage());
        }
    }
}