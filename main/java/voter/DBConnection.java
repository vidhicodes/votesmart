package main.java.voter;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/vote_smart";
    private static final String USER = "root";
    private static final String PASSWORD = "vidhi@10";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection Error Occurred.");
            return null;
        }
    }
}

