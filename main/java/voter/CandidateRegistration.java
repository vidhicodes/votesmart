package main.java.voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static main.java.voter.DBConnection.getConnection;

class InvalidCandidateException extends Exception{
    public InvalidCandidateException(String message) {
        super(message);
    }

}

public class CandidateRegistration {

    public static void registerCandidate(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Enter your party: ");
        String party = sc.nextLine();

        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println();

        System.out.println("Enter your contested position: ");
        String contested_position = sc.nextLine();

        try {
            validateCandidate(age);
        } catch (InvalidCandidateException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        try (Connection connection = getConnection()) {
            String query = "INSERT INTO candidates (name, age, party, contested_position) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, party);
            preparedStatement.setString(4, contested_position);

            int rows_affected = preparedStatement.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Registration successful.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        sc.close();
    }

    static void validateCandidate(int age) throws InvalidCandidateException{
        if(age < 25){
            throw new InvalidCandidateException("Candidates must be 25 or older to register.");
        }
    }
}
