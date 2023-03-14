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
    public TableView<ObservableList<String>> table_single;
    @FXML
    public TableColumn<ObservableList<String>, String> id_single;
    @FXML
    public TableColumn<ObservableList<String>, String> game_single;

    @FXML
    public TableView<ObservableList<String>> table_team;
    @FXML
    public TableColumn<ObservableList<String>, String> id_team;
    @FXML
    public TableColumn<ObservableList<String>, String> game_team;

    @FXML
    public Button btn_logout;



    public void initialize() throws SQLException {
        log_name.setText("Prijavljeni ste kot:  " + user_saved.getUsername());


// Set up the cell value factories to extract the values from the ObservableList of ObservableLists
        id_single.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(0)));
        game_single.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(1)));

        id_team.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(0)));
        game_team.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(1)));


// Set the data source of the TableView to the returned ObservableList
        table_single.setItems(JavaPostgreSql.getGames(false));
        table_team.setItems(JavaPostgreSql.getGames(true));
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
}
