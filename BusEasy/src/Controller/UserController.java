package Controller;

import Model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static final String URL = "jdbc:mysql://localhost:3306/BusEasy2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    // Get Database Connection
    
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add User
    public static boolean addUser(User user) {
        String query = "INSERT INTO user (firstname, lastname, username, password, contactnumber, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getFname());
            stmt.setString(2, user.getLname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, hashPassword(user.getPassword())); // Hash password
            stmt.setInt(5, user.getContact());
            stmt.setString(6, user.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get All Users
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT userid, firstname, lastname, username, password, contactnumber, role FROM user";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("userid"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("contactnumber"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update User
    public static boolean updateUser(User user) {
        String query = "UPDATE user SET firstname = ?, lastname = ?, username = ?, password = ?, contactnumber = ?, role = ? WHERE userid = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getFname());
            stmt.setString(2, user.getLname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, hashPassword(user.getPassword())); // Hash password
            stmt.setInt(5, user.getContact());
            stmt.setString(6, user.getRole());
            stmt.setInt(7, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete User
    public static boolean deleteUser(int userId) {
        String query = "DELETE FROM user WHERE userid = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find User by ID
    public static User findUserById(int userId) {
        String query = "SELECT userid, firstname, lastname, username, password, contactnumber, role FROM user WHERE userid = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("userid"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("contactnumber"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hash Password
    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verify Password
    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }
}