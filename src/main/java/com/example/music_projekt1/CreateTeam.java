package com.example.music_projekt1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CreateTeam {

    @FXML
    public Label game_tournament_label;
    public PasswordField password_field;
    public TextField teamName_field;
    public Button selectFile_btn;
    public Label image_label;
    public Button create_btn;
    public Button cancel_btn;


    public void initialize() {
        game_tournament_label.setText(saved.getGameChosen() + " - " + saved.getTournament() + " Tournament");
    }

    public void createTeam() throws IOException, NoSuchAlgorithmException {
        if (!teamName_field.getText().isEmpty() || !password_field.getText().isEmpty()){
            String teamName = teamName_field.getText();
            String password = password_field.getText();
            String _password = PasswordHasher.hashPassword(password);
            JavaPostgreSql.createTeam(teamName, _password, saved.getTournamentID());
            JavaPostgreSql.joinTeam(saved.getTeamID(), saved.getUserID());

            Stage currentStage = (Stage) create_btn.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(true);
            stage.setTitle("Lan Party");
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("Please fill all fields");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill all fields");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }

    public void cancel() throws IOException {
        Stage currentStage = (Stage) cancel_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TeamMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Create Team");
        stage.setScene(scene);
        stage.show();
    }
}
