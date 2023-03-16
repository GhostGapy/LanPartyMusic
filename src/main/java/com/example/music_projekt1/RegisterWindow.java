package com.example.music_projekt1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RegisterWindow {
    @FXML
    private Button btn_register_complete;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField password1;
    @FXML
    private TextField password2;




    @FXML
    protected void getData() throws NoSuchAlgorithmException, IOException {
        System.out.println(name.getText());
        System.out.println(surname.getText());
        System.out.println(password1.getText());
        System.out.println(password2.getText());

        if(!name.getText().isEmpty() || !surname.getText().isEmpty() || !password1.getText().isEmpty() || !password2.getText().isEmpty()){
            if (!(JavaPostgreSql.freeName(name.getText(), surname.getText()))){
                if ((password1.getText()).equals(password2.getText()))
                {
                    System.out.println("Passwords are the same");
                    JavaPostgreSql.Register(name.getText(), surname.getText(),  password1.getText());

                    Stage currentStage = (Stage) btn_register_complete.getScene().getWindow();
                    currentStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Lan Party");
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    System.out.println("Passwords are not the same");
                    name.clear();
                    surname.clear();
                    password1.clear();
                    password2.clear();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Passwords are not the same");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                }
            }
            else{
                System.out.println("Username is already taken");
                name.clear();
                surname.clear();
                password1.clear();
                password2.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username is already taken");
                alert.setContentText("Please try again");
                alert.showAndWait();

            }

        }
        else
        {
            System.out.println("Please fill all fields");
            name.clear();
            surname.clear();
            password1.clear();
            password2.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill all fields");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }

    }

}