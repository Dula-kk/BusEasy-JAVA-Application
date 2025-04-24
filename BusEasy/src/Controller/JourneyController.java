package Controller;

import Model.Journey;
import View.JourneyView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JourneyController {
    private static  String URL = "jdbc:mysql://localhost:3306/BusEasy2";
    private static  String USER = "root";
    private static  String PASSWORD = "";
    private static Connection connection;
    private JourneyView view;

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

    public static List<Journey> getAllJourneys() {
    List<Journey> journeys = new ArrayList<>();
    String query = "SELECT * FROM journey";
    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Journey journey = new Journey();
            journey.setJourneyId(rs.getInt("journeyid"));
            journey.setRoute(rs.getString("route"));
            journey.setDepartureTime(rs.getTimestamp("departure_time"));
            journey.setArrivalTime(rs.getTimestamp("arrival_time"));
            journey.setStatus(rs.getString("status"));
            journey.setConductorId(rs.getInt("conductorid"));
            journey.setFee(rs.getDouble("fee"));
            journey.setCreatedAt(rs.getTimestamp("created_at"));
            journeys.add(journey);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Failed to fetch journeys: " + e.getMessage(), 
            "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    return journeys;
}

    public boolean updateJourneyStatus(int journeyId, String status) {
    String query = "UPDATE journey SET status = ? WHERE journeyid = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, status);
        stmt.setInt(2, journeyId);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0; // Return true if at least one row was updated
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Failed to update journey status: " + e.getMessage(), 
            "Database Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

   public void loadJourneysIntoTable(JTable table) {
    if (table == null) {
        System.err.println("JTable is null");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    if (model == null) {
        System.err.println("Table model is null");
        return;
    }

    model.setRowCount(0); // Clear existing data
    List<Journey> journeys = getAllJourneys();

    for (Journey journey : journeys) {
        model.addRow(new Object[]{
            journey.getJourneyId(),
            journey.getRoute(),
            journey.getDepartureTime(),
            journey.getArrivalTime(),
            journey.getStatus(),
            journey.getConductorId(),
            journey.getFee()
        });
    }
}
    
}