/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.DashboardData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Geeshanu Gayan
 */
public class DashboardController {
     private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/BusEasy2";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
}
    public DashboardData getDashboardData() {
        DashboardData data = new DashboardData();
        try (Connection conn = getConnection()) {
            // Fetch total income
            String incomeQuery = "SELECT SUM(amount) AS total_income FROM payment";
            try (PreparedStatement pstmt = conn.prepareStatement(incomeQuery)) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    data.setTotalIncome(rs.getDouble("total_income"));
                }
            }

            // Fetch total journeys
            String totalJourneysQuery = "SELECT COUNT(*) AS total_journeys FROM journey WHERE status = 'scheduled'";
            try (PreparedStatement pstmt = conn.prepareStatement(totalJourneysQuery)) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    data.setTotalJourneys(rs.getInt("total_journeys"));
                }
            }

            // Fetch total reservations
            String reservationsQuery = "SELECT COUNT(*) AS total_reservations FROM reservation";
            try (PreparedStatement pstmt = conn.prepareStatement(reservationsQuery)) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    data.setTotalReservations(rs.getInt("total_reservations"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}