package com.example.music_projekt1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class HelloController {
    @FXML
    private Button btn_register;
    @FXML
    private Button btn_login;




    @FXML
    protected void openRegisterWindow() throws IOException {
        Stage currentStage = (Stage) btn_register.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("REGISTER");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    protected void openLoginWindow() throws IOException {
        Stage currentStage = (Stage) btn_login.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
    }

}