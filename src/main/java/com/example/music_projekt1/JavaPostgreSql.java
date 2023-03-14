package com.example.music_projekt1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSql {
    public static void Register(String name, String surname, String userPassword1) throws NoSuchAlgorithmException {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        String pass1 = PasswordHasher.hashPassword(userPassword1);

        String query = "INSERT INTO users(name, surname, password) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setString(3, pass1);
            pst.executeUpdate();
            System.out.println("Successfully created.");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful Registered");
            alert.setHeaderText("You have successfully registered, your username is: " + name.toLowerCase() + "." + surname.toLowerCase());
            alert.setContentText("You can now login");
            alert.showAndWait();
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("There was an error");
            alert.setContentText("Please try again");
            alert.showAndWait();

        }
    }

    public static boolean freeName(String name, String surname){
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        String query = "SELECT name, surname FROM users WHERE name = ? AND surname = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, surname);
            ResultSet rs = pst.executeQuery();

            return rs.next();
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("There was an error / username is taken");
            alert.setContentText("Please try again");
            alert.showAndWait();
            return false;
        }
    }

    public static boolean Login(String _username, String userPassword1) throws NoSuchAlgorithmException {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        String query = "SELECT password FROM users WHERE username = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, _username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String correctPassword = rs.getString("password");
                String pass1 = PasswordHasher.hashPassword(userPassword1);
                if (pass1.equals(correctPassword)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successful Login");
                    alert.setHeaderText("You have successfully logged in");
                    alert.setContentText("You can now continue");
                    alert.showAndWait();

                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Passwords do not match");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                    return false;
                }
            } else {
                // No matching user was found
                System.out.println("No matching user was found");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("No matching user was found");
                alert.setContentText("Please try again");
                alert.showAndWait();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }

    }

    public static String[] getDatabase() throws SQLException {
        String url = "jdbc:postgresql://ep-purple-breeze-177741.eu-central-1.aws.neon.tech/neondb";
        String user = "GhostGapy";
        String password = "G4XZhDPTB0WC";

        String username = "";

        String query = "SELECT username FROM users WHERE username = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("There was an error");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }

        return new String[0];
    }

    public static ObservableList<ObservableList<String>> getGames(boolean x) throws SQLException {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        String query;
        if(x)
        {
            query = "SELECT id, name FROM games WHERE team_size = 1";
        }
        else{
            query = "SELECT id, name FROM games WHERE team_size > 1";
        }
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                ObservableList<String> row = FXCollections.observableArrayList(id, name);
                data.add(row);
            }
        }
        return data;
    }

}

