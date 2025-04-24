package Controller;

import Model.Reservation;
import View.ReservationView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing reservations.
 */
public class ReservationController {
    private static  String URL = "jdbc:mysql://localhost:3306/BusEasy2";
    private static  String USER = "root";
    private static  String PASSWORD = "";
    private static Connection connection;
    private ReservationView view;

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
    /**
     * Retrieves all reservations from the database.
     *
     * @return List of reservations.
     */
    public static List<Reservation> getAllReservations() {
    List<Reservation> reservations = new ArrayList<>();
    String query = "SELECT * FROM Reservation";

    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        System.out.println("Executing Reservation query: " + query); 

        while (rs.next()) {
            System.out.println("Found reservation with ID: " + rs.getInt("reservationid")); 
            reservations.add(new Reservation(
                rs.getInt("reservationid"),
                rs.getInt("userid"),
                rs.getInt("journeyid"),
                rs.getString("seats"),
                rs.getString("payment_status"),
                rs.getTimestamp("created_at")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error executing Reservation query: " + e.getMessage());
        e.printStackTrace();
    }

    return reservations;
}
}
