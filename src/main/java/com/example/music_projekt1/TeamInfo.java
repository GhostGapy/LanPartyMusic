package com.example.music_projekt1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TeamInfo {

    @FXML
    public Label tournament_name;
    @FXML
    public Label g_t_label;
    @FXML
    public ListView<String> listViewPlayers;
    @FXML
    public PasswordField pass;
    @FXML
    public Button join_btn;
    @FXML
    public Button back_btn;

    public void initialize() throws SQLException {
        System.out.println(saved.getTeamID());
        System.out.println(saved.getTeam());

        tournament_name.setText(saved.getTeam());
        g_t_label.setText(saved.getGameChosen() + " - " + saved.getTournament() + " Tournament");

        Integer teamID = saved.getTeamID();
        ObservableList<String> players = JavaPostgreSql.getPlayers(teamID);
        listViewPlayers.getItems().addAll(players);
    }

    @FXML
    protected void cancel() throws IOException {
        saved.setTeam("");
        saved.setTeamID(null);

        Stage currentStage = (Stage) back_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void join() throws IOException, NoSuchAlgorithmException {
        String _password = pass.getText();
        String password = PasswordHasher.hashPassword(_password);
        Integer teamID = saved.getTeamID();
        String team = saved.getTeam();
        Integer userID = saved.getUserID();

        if (JavaPostgreSql.checkPasswordTeam(password, teamID, userID)) {
            saved.setTeam(team);
            saved.setTeamID(teamID);

            Stage currentStage = (Stage) join_btn.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(true);
            stage.setTitle("Lan Party");
            stage.setScene(scene);
            stage.show();
        } else {
            pass.setText("");
            pass.setPromptText("Wrong password");
            pass.setAlignment(Pos.CENTER);
        }
    }

}


