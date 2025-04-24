/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Admin;
import View.AdminDashboard;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * AdminController: Handles admin-related logic and database queries.
 * @author Geeshanu
 */
public class AdminController {

    private static Connection connection;

    // Static block to initialize the database connection
    static {
        try {
            String url = "jdbc:mysql://localhost:3306/BusEasy2";
            String user = "root"; 
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established successfully.");
        } catch (SQLException ex) {
            System.err.println("Failed to establish database connection: " + ex.getMessage());
            throw new RuntimeException("Database connection failed", ex);
        }
    }

    // Login Method
    public void login(String username, String password) {
        Admin admin = getAdminByUsername(username);

        if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {
            // Successful login
            JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Open the Admin Dashboard
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true); // Show Admin Dashboard
        } else {
            // Failed login
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fetch Admin Details by Username
    private Admin getAdminByUsername(String username) {
        Admin admin = null;

        String query = "SELECT username, password FROM admin WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password"); // Hashed password
                admin = new Admin(dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

}
