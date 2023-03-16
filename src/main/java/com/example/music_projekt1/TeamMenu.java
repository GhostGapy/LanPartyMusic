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

public class TeamMenu {
    @FXML
    public Label tournament_name;
    @FXML
    public TableView<ObservableList<String>> table;
    @FXML
    public TableColumn<ObservableList<String>, String> id;
    @FXML
    public TableColumn<ObservableList<String>, String> name;
    @FXML
    public TableColumn<ObservableList<String>, String> num_players;
    @FXML
    public Button return_btn;

    public void initialize() throws SQLException{

            tournament_name.setText(saved.getGameChosen() + " - " + saved.getTournament() + " Tournament");
            // Set up the cell value factories to extract the values from the ObservableList of ObservableLists
            id.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(0)));
            name.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(1)));
            num_players.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(2)));

            // Set the data source of the TableView to the returned ObservableList
            table.setItems(JavaPostgreSql.getTeams(saved.getTournamentID()));

            table.setRowFactory( tv -> {
                TableRow<ObservableList<String>> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        ObservableList<String> rowData = row.getItem();

                        String team_name = rowData.get(1);
                        saved.setTeam(team_name);

                        Integer teamID = Integer.parseInt(rowData.get(0));
                        saved.setTeamID(teamID);
                        try {
                            team_enter();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                return row;
            });
    }

    @FXML
    protected void team_enter() throws IOException {
        Stage currentStage = (Stage) return_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void cancel() throws IOException {
        saved.setTournament("");
        saved.setTournamentID(null);

        Stage currentStage = (Stage) return_btn.getScene().getWindow();
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
