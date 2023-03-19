package com.example.music_projekt1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSql {

    public static Integer getUserID(String _username) {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
        Integer userID = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT id FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, _username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userID = resultSet.getInt("id");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }


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



    public static ObservableList<ObservableList<String>> getGames() throws SQLException {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        String query = "SELECT id, name, team_size FROM games;";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String team_size = rs.getString("team_size");
                ObservableList<String> row = FXCollections.observableArrayList(id, name, team_size);
                data.add(row);
            }
        }
        return data;
    }


    public static ObservableList<ObservableList<String>> getTournaments(Integer g_id) throws SQLException {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        String query = "SELECT id, name, num_teams FROM tournaments WHERE game_id = " + g_id + ";";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String num_teams = rs.getString("num_teams");
                ObservableList<String> row = FXCollections.observableArrayList(id, name, num_teams);
                data.add(row);
            }
        }
        return data;
    }

    public static ObservableList<ObservableList<String>> getTeams(Integer t_id) throws SQLException{
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        String query = "SELECT id, name, num_players FROM teams WHERE tournament_id = " + t_id + ";";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("id");
                String num_players = rs.getString("num_players");
                ObservableList<String> row = FXCollections.observableArrayList(id, name, num_players);
                data.add(row);
            }
        }
        return data;
    }

    public static ObservableList<String> getPlayers(Integer t_id) throws SQLException {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
        ObservableList<String> data = FXCollections.observableArrayList();

        String query = "SELECT name FROM players WHERE team_id = " + t_id + ";";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                data.add(name);
            }
        }
        return data;
    }

    public static boolean checkPasswordTeam(String _password, Integer teamID, Integer userID) {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        if (checkUserTeam(userID)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You are already in a team");
            alert.setContentText("Please try again");
            alert.showAndWait();
            return false;
        } else {
            String query = "SELECT team_pass FROM teams WHERE id = ?";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pst = con.prepareStatement(query)) {

                pst.setInt(1, teamID);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String correctPassword = rs.getString("team_pass");
                    String pass1 = PasswordHasher.hashPassword(_password);
                    System.out.println(pass1 + "  " + correctPassword);
                    if (pass1.equals(correctPassword)) {
                        joinTeam(teamID, userID);
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
                    System.out.println("No matching team was found");

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("No matching team was found");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void joinTeam(Integer teamID, Integer userID) {
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

            String query = "INSERT INTO players (name, kraj_id, user_id, team_id) VALUES ((SELECT username FROM users WHERE id=?), 431, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, userID);
                stmt.setInt(2, userID);
                stmt.setInt(3, teamID);

                int affectedRows = stmt.executeUpdate();

                if (affectedRows == 1) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Player joined successfully!");
                    successAlert.showAndWait();
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Failed to add player to the database.");
                    errorAlert.showAndWait();
                }

            } catch (SQLException ex) {
                Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
                exceptionAlert.setTitle("Exception");
                exceptionAlert.setHeaderText(null);
                exceptionAlert.setContentText("An exception occurred while accessing the database: " + ex.getMessage());
                exceptionAlert.showAndWait();
            }
        }

    public static boolean checkUserTeam(Integer userID){
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT COUNT(*) FROM players WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

