package Controller;

import Model.Payment;
import View.PaymentView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PaymentController {
    private static final String URL = "jdbc:mysql://localhost:3306/BusEasy2";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    private PaymentView view;

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

    public PaymentController(PaymentView view) {
        this.view = view;
    }

    public void loadPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment ORDER BY payment_date DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("paymentid"),
                    rs.getInt("reservationid"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("status"),
                    rs.getTimestamp("payment_date")
                );
                payments.add(payment);
            }
            view.updatePaymentTable(payments);
            
        } catch (SQLException e) {
            showError("Error loading payments: " + e.getMessage());
        }
    }

    public void approvePayment(int paymentId) {
        String query = "UPDATE Payment SET status = 'completed' WHERE paymentid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, paymentId);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(view, 
                    "Payment approved successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadPayments();
            } else {
                showError("Payment not found or already approved.");
            }
        } catch (SQLException e) {
            showError("Error approving payment: " + e.getMessage());
        }
    }

    public void rejectPayment(int paymentId) {
        String query = "UPDATE Payment SET status = 'rejected' WHERE paymentid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, paymentId);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(view, 
                    "Payment rejected successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadPayments();
            } else {
                showError("Payment not found or already rejected.");
            }
        } catch (SQLException e) {
            showError("Error rejecting payment: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}