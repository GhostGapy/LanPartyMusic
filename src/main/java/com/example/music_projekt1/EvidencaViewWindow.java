package com.example.music_projekt1;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EvidencaViewWindow {

    @FXML
    public Label log_name;
    @FXML
    public Label test;
    @FXML
    public TableView<ObservableList<String>> table;
    @FXML
    public TableColumn<ObservableList<String>, String> id;
    @FXML
    public TableColumn<ObservableList<String>, String> game;
    @FXML
    public TableColumn<ObservableList<String>, String> team_size;

    @FXML
    public Button btn_logout;
    @FXML
    public Button enter_btn;



    public void initialize() throws SQLException {
        log_name.setText("Prijavljeni ste kot:  " + user_saved.getUsername());


// Set up the cell value factories to extract the values from the ObservableList of ObservableLists
        id.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(0)));
        game.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(1)));
        team_size.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(2)));



// Set the data source of the TableView to the returned ObservableList
        table.setItems(JavaPostgreSql.getGames());
    }

    @FXML
    protected void cancel() throws IOException {
        user_saved.setUsername("");

        Stage currentStage = (Stage) btn_logout.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void tournament_enter() throws IOException {
        Stage currentStage = (Stage) btn_logout.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tournamentMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }
}
