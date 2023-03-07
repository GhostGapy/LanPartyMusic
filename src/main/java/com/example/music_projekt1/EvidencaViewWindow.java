package com.example.music_projekt1;

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

public class EvidencaViewWindow {

    @FXML
    public Label log_name;

    @FXML
    public Button btn_logout;

    public TableView<evidenca> table;
    public TableColumn<evidenca, Integer> id;
    public TableColumn<evidenca, String> ime;
    public TableColumn<evidenca, String> zdravniki;
    public TableColumn<evidenca, String> naslov;
    public TableColumn<evidenca, String> kraj;
    public TableColumn<evidenca, String> vrsta;


    public void initialize() {
        log_name.setText("   Prijavljeni ste kot:  " + user_saved.getUsername());
        id.setCellValueFactory(new PropertyValueFactory<evidenca, Integer>("id"));
        ime.setCellValueFactory(new PropertyValueFactory<evidenca, String>("ime"));
        zdravniki.setCellValueFactory(new PropertyValueFactory<evidenca, String>("zdravniki"));
        naslov.setCellValueFactory(new PropertyValueFactory<evidenca, String>("naslov"));
        kraj.setCellValueFactory(new PropertyValueFactory<evidenca, String>("kraj"));
        vrsta.setCellValueFactory(new PropertyValueFactory<evidenca, String>("vrsta"));
        ObservableList<evidenca> data = table.getItems();

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
        stage.setTitle("EVIDENCA ZDRAVNIŠKIH ORDINACIJ");
        stage.setScene(scene);
        stage.show();

    }
}
