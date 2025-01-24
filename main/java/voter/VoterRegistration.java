package main.java.voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static main.java.voter.DBConnection.getConnection;

public class VoterRegistration {

    public static void registerVoter(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Enter your email: ");
        String email = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();

        if(age<18){
            System.out.println("You must be 18 or older to register.");
        }
        if(!email.contains("@")){
            System.out.println("Invalid email format.");
        }

        try(Connection connection = getConnection()) {

            String query = "INSERT INTO voters (name,email,password,age) VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            preparedStatement.setInt(4,age);

            int rows_affected = preparedStatement.executeUpdate();

            if (rows_affected>0){
                System.out.println("Registration successful.");
            }else {
                System.out.println("Registration failed. Please try again.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Already Registered.3");
        }

        sc.close();

    }

}
