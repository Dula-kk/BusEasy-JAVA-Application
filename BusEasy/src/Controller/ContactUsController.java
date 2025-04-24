package Controller;

import Model.ContactUs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactUsController {
    private static final String URL = "jdbc:mysql://localhost:3306/BusEasy2";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection failed: " + ex.getMessage());
            throw new RuntimeException("Database connection failed", ex);
        }
    }

    public List<ContactUs> getAllContactUsDetails() throws SQLException {
        List<ContactUs> contactUsList = new ArrayList<>();
        String query = "SELECT * FROM contact ORDER BY created_at DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContactUs contact = new ContactUs(
                    rs.getString("contactid"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("message"),
                    rs.getTimestamp("created_at")
                );
                contactUsList.add(contact);
            }
        }
        return contactUsList;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}