import main.java.voter.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to VoteSmart!");
            System.out.println("1. Register Voter");
            System.out.println("2. Register Candidate");
            System.out.println("3. Login");
            System.out.println("4. Cast Vote");
            System.out.println("5. Display Results");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            String choiceInput = sc.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                continue;
            }

            System.out.println();

            try {
                switch (choice) {
                    case 1:
                        VoterRegistration.registerVoter();
                        break;
                    case 2:
                        CandidateRegistration.registerCandidate();
                        break;
                    case 3:
                        Login.checkCredentials();
                        break;
                    case 4:
                        System.out.print("Enter your voter ID: ");
                        String voterIdInput = sc.nextLine();
                        int voterId;
                        try {
                            voterId = Integer.parseInt(voterIdInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid voter ID. Please enter a valid number.\n");
                            break;
                        }
                        Voting.castVote(voterId);
                        break;
                    case 5:
                        Result.displayResults();
                        break;
                    case 6:
                        System.out.println("Exiting... Thank you for using the system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
