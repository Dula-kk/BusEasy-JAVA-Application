package Controller;

import Model.Refund;
import View.RefundView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RefundController {
    private static final String URL = "jdbc:mysql://localhost:3306/BusEasy2";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private RefundView view;

    public RefundController(RefundView view) {
        this.view = view;
    }

    public void loadRefunds() {
        List<Refund> refunds = new ArrayList<>();
        String query = "SELECT * FROM refund ";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Refund refund = new Refund(
                    rs.getInt("refundid"),
                    rs.getInt("reservationid"),
                    rs.getString("status")
                );
                refunds.add(refund);
            }
            view.updateRefundTable(refunds);

        } catch (SQLException e) {
            showError("Error loading refunds: " + e.getMessage());
        }
    }

    public void approveRefund(int refundId) {
        String query = "UPDATE refund SET status = 'approved' WHERE refundid = ? ";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, refundId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(view,
                    "Refund approved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                loadRefunds();
            } else {
                showError("Refund not found or already approved.");
            }
        } catch (SQLException e) {
            showError("Error approving refund: " + e.getMessage());
        }
    }

    public void rejectRefund(int refundId) {
        String query = "UPDATE refund SET status = 'rejected' WHERE refundid = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, refundId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(view,
                    "Refund rejected successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                loadRefunds();
            } else {
                showError("Refund not found or already rejected.");
            }
        } catch (SQLException e) {
            showError("Error rejecting refund: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}