package com.example.music_projekt1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTeam {

    @FXML
    public Label game_tournament_label;
    public PasswordField password_field;
    public TextField teamName_field;
    public Button selectFile_btn;
    public Label image_label;
    public Button create_btn;
    public Button cancel_btn;

    public BufferedImage bImage2 = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
    public File file;


    public void initialize() {
        game_tournament_label.setText(saved.getGameChosen() + " - " + saved.getTournament() + " Tournament");
    }

    public void createTeam() throws IOException, NoSuchAlgorithmException, SQLException {
        if (!teamName_field.getText().isEmpty() || !password_field.getText().isEmpty()){
            String teamName = teamName_field.getText();
            String password = password_field.getText();
            String _password = PasswordHasher.hashPassword(password);
            JavaPostgreSql.createTeam(teamName, _password, saved.getTournamentID());
            JavaPostgreSql.joinTeam(saved.getTeamID(), saved.getUserID());

            String sql = "UPDATE teams SET image = ? WHERE id = ?";
            String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
            String user = "demvidab";
            String _password_ = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
            Connection conn = DriverManager.getConnection(url, user, _password_);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            System.out.print("Success");
            FileInputStream fin = new FileInputStream(file);

            preparedStatement.setBinaryStream(1, fin, (int) file.length());
            preparedStatement.setInt(2,saved.getTeamID());
            System.out.print("Success");
            preparedStatement.executeUpdate();
            System.out.print("Success");

            conn.close();
            System.out.print("Success");

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


    @FXML
    protected void editimg() {
        FileChooser fileChooser = new FileChooser();

// set the title of the file chooser dialog
        fileChooser.setTitle("Open Image File");

// set the initial directory of the file chooser
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

// set the file extension filter to limit the selectable files to images
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

// get the selected file from the file chooser dialog
        file = fileChooser.showOpenDialog(new Stage());

        image_label.setText(file.getName());
    }
}
