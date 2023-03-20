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

    public void initialize() throws SQLException {

        game_name.setText(saved.getGameChosen());
// Set up the cell value factories to extract the values from the ObservableList of ObservableLists
        id.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(0)));
        name.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(1)));
        num_teams.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(2)));

// Set the data source of the TableView to the returned ObservableList
        table.setItems(JavaPostgreSql.getTournaments(saved.getGameID()));

        table.setRowFactory( tv -> {
            TableRow<ObservableList<String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ObservableList<String> rowData = row.getItem();

                    String tournament_game = rowData.get(1);
                    saved.setTournament(tournament_game);

                    Integer TournamentID = Integer.parseInt(rowData.get(0));
                    saved.setTournamentID(TournamentID);
                    try {
                        game_enter();
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
        saved.setGameChosen("");
        saved.setGameIDChosen(null);

        Stage currentStage = (Stage) return_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("evidencaViewWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(true);
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
        stage.setResizable(true);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }
}
