package com.example.music_projekt1;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EvidencaViewWindow {

    @FXML
    public Label log_name;
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



    public void initialize() throws SQLException {
        log_name.setText("Prijavljeni ste kot:  " + saved.getUsername());


// Set up the cell value factories to extract the values from the ObservableList of ObservableLists
        id.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(0)));
        game.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(1)));
        team_size.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(2)));

// Set the data source of the TableView to the returned ObservableList
        table.setItems(JavaPostgreSql.getGames());

        table.setRowFactory( tv -> {
            TableRow<ObservableList<String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ObservableList<String> rowData = row.getItem();

                    String game_name = rowData.get(1);
                    saved.setGameChosen(game_name);

                    Integer GameID = Integer.parseInt(rowData.get(0));
                    saved.setGameIDChosen(GameID);
                    try {
                        tournament_enter();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }

    @FXML
    protected void cancel() throws IOException {
        saved.setUsername("");
        saved.setGameIDChosen(null);

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
    public void tournament_enter() throws IOException {
        Stage currentStage = (Stage) btn_logout.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tournamentMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }


}
