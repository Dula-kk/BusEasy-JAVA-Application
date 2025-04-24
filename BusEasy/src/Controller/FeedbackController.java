package Controller;

import Model.Feedback;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackController {
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

    public List<Feedback> getAllFeedbackDetails() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT * FROM feedback ORDER BY created_at DESC";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getInt("feedbackid"),
                    rs.getInt("reservationid"),
                    rs.getString("message"),
                    rs.getTimestamp("created_at")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error fetching feedback: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return feedbackList;
    }
}