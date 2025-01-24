package main.java.voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.java.voter.DBConnection.getConnection;

public class Result {
    public static void displayResults() throws SQLException {

        Connection connection = getConnection();

        String query = "SELECT contested_position,party,name,votes_count FROM candidates ORDER BY contested_position,votes_count DESC;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("Election Results: ");
        System.out.println("Position\tParty\tName\tVotes");
        while (resultSet.next()) {
            System.out.printf("%s\t%s\t%s\t%d\n",
                    resultSet.getString("contested_position"),
                    resultSet.getString("party"),
                    resultSet.getString("name"),
                    resultSet.getInt("votes_count"));
        }
    }
}
