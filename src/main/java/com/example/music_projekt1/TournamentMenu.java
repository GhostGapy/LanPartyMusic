package com.example.music_projekt1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class TournamentMenu {

    @FXML
    public Label game_name;
    @FXML
    public TableView<ObservableList<String>> table;
    @FXML
    public TableColumn<ObservableList<String>, String> id;
    @FXML
    public TableColumn<ObservableList<String>, String> name;
    @FXML
    public TableColumn<ObservableList<String>, String> num_teams;
    @FXML
    public Button return_btn;

    @FXML
    protected void cancel() throws IOException {

        Stage currentStage = (Stage) return_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("evidencaViewWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void game_enter() throws IOException {
        Stage currentStage = (Stage) return_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }
}
