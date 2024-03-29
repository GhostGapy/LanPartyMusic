package com.example.music_projekt1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginWindow {
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField username;
    @FXML
    private TextField password1;


    @FXML
    protected void getData() throws NoSuchAlgorithmException, IOException {
        System.out.println(username.getText());
        System.out.println(password1.getText());
        boolean isLogin = JavaPostgreSql.Login(username.getText(), password1.getText());

        if (isLogin) {
            System.out.println("Login successful");

            saved.setUsername(username.getText());
            Integer userID = JavaPostgreSql.getUserID(username.getText());
            saved.setUserID(userID);

            Stage currentStage = (Stage) btn_cancel.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("evidencaViewWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(true);
            stage.setTitle("LAN PARTY");
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("Login failed");
            username.clear();
            password1.clear();
        }

    }

    @FXML
    protected void cancel() throws IOException {
        Stage currentStage = (Stage) btn_cancel.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("LAN PARTY");
        stage.setScene(scene);
        stage.show();
    }


}