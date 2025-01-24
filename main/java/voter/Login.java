package main.java.voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static main.java.voter.DBConnection.getConnection;

public class Login {

    public static void checkCredentials() {

        Scanner sc = new Scanner(System.in);

        // read email and password from the user
        System.out.println("Enter your email:");
        String email = sc.nextLine().trim();

        System.out.println("Enter your password:");
        String password = sc.nextLine().trim();


        try (Connection connection = getConnection()) {
            // SQL query to validate user credentials
            String query = "SELECT voter_id, name FROM voters WHERE email = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int voterId = resultSet.getInt("voter_id");
                String name = resultSet.getString("name");

                //successful message
                System.out.println("Login successful. Welcome, " + name + "!");
                System.out.println("Your Voter ID: " + voterId);
            } else {
                // Handle incorrect credentials
                System.out.println("Incorrect credentials. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while logging in", e);
        }

        sc.close();
    }
}
