package com.example.music_projekt1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private TextField password1;
    @FXML
    private TextField password2;




    @FXML
    protected void getData() {
        System.out.println(username.getText());
        System.out.println(password1.getText());
        System.out.println(password2.getText());

        if ((password1.getText()).equals(password2.getText())) {
            System.out.println("Passwords are the same");
            JavaPostgreSql.WriteToDatabase(username.getText(), password1.getText(), password2.getText());
        }
        else{
            System.out.println("Passwords are not the same");
            username.clear();
            password1.clear();
            password2.clear();
        }


    }
}