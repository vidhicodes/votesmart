package main.java.voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static main.java.voter.DBConnection.getConnection;

public class Voting {
    public static void castVote(int voter_id){

        Scanner sc = new Scanner(System.in);

        Connection connection = getConnection();

        if(hasAlreadyVoted(connection,voter_id)){
            System.out.println("You have already voted.");
        }
        displayCandidates(connection);

        System.out.println("Enter Candidate ID you want to vote for: ");
        int candidate_id = sc.nextInt();

        if(isValidCandidate(connection,candidate_id)){
            incrementVote(connection,candidate_id);
        }else{
            System.out.println("Invalid Candidate ID.");
        }


    }

    private static boolean hasAlreadyVoted(Connection connection,int voter_id){

        String query = "SELECT * FROM votes WHERE voter_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,voter_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void displayCandidates(Connection connection){
        String query = "SELECT candidate_id,name,party,contested_position FROM candidates;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Candidates List: ");
            System.out.println("ID\tName\tParty\tPosition");
            while (resultSet.next()) {
                System.out.printf("%d\t%s\t%s\t%s\n",
                        resultSet.getInt("candidate_id"),
                        resultSet.getString("name"),
                        resultSet.getString("party"),
                        resultSet.getString("contested_position"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void incrementVote(Connection connection,int candidate_id){
        String query = "UPDATE candidates SET votes_count = votes_count + 1 where candidate_id=?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,candidate_id);
            int rows_affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isValidCandidate(Connection connection,int candidate_id){
        String query = "SELECT * FROM candidates WHERE candidate_id=?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,candidate_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
