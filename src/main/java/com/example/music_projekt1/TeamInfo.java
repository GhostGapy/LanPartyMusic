package com.example.music_projekt1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.awt.*;

public class TeamInfo {
    @FXML
    public BufferedImage bImage2 = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
    public File file;
    @FXML
    public Label tournament_name;
    @FXML
    public ImageView teamimg;
    @FXML
    public Label g_t_label;
    @FXML
    public ListView<String> listViewPlayers;
    @FXML
    public PasswordField pass;
    @FXML
    public Button join_btn;
    @FXML
    public Button back_btn;

    @FXML
    public Button editimg_btn;

    public void initialize() throws SQLException {
        System.out.println(saved.getTeamID());
        System.out.println(saved.getTeam());

        tournament_name.setText(saved.getTeam());
        g_t_label.setText(saved.getGameChosen() + " - " + saved.getTournament() + " Tournament");

        Integer teamID = saved.getTeamID();
        ObservableList<String> players = JavaPostgreSql.getPlayers(teamID);
        listViewPlayers.getItems().addAll(players);
    }

    @FXML
    protected void cancel() throws IOException {
        saved.setTeam("");
        saved.setTeamID(null);

        Stage currentStage = (Stage) back_btn.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lan Party");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void editimg() throws SQLException, IOException {
        FileChooser fileChooser = new FileChooser();

// set the title of the file chooser dialog
        fileChooser.setTitle("Open Image File");

// set the initial directory of the file chooser
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

// set the file extension filter to limit the selectable files to images
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

// get the selected file from the file chooser dialog
        file = fileChooser.showOpenDialog(new Stage());
        String sql = "UPDATE teams SET image = ? WHERE id = ?";
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        System.out.print("Success");
        FileInputStream fin = new FileInputStream(file);

            preparedStatement.setBinaryStream(1, fin, (int) file.length());
            preparedStatement.setInt(2,saved.getTeamID());
        System.out.print("Success");
            preparedStatement.executeUpdate();
        System.out.print("Success");
            setImage();

        conn.close();
        System.out.print("Success");

    }
    private void setImage() throws SQLException, IOException {
        String sql="SELECT image, LENGTH(image) FROM teams WHERE id = ?";
        String url = "jdbc:postgresql://rogue.db.elephantsql.com/demvidab";
        String user = "demvidab";
        String password = "ve4aywwgYviI10jTDn92Q8ABSZBcHtoO";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, saved.getTeamID());
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
        {
            int len = rs.getInt(2);
            byte[] buf = rs.getBytes("image");
            if (buf != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(buf);
                bImage2 = ImageIO.read(bis);
                bImage2 = resizeImage(bImage2, 300, 300);
            }
            Image image = convertToFxImage(bImage2);
            teamimg.setImage(image);
            conn.close();
            System.out.println("Succenss!");
        }
    }
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        return new ImageView(wr).getImage();
    }
    @FXML
    protected void join() throws IOException, NoSuchAlgorithmException {
        String _password = pass.getText();
        String password = PasswordHasher.hashPassword(_password);
        Integer teamID = saved.getTeamID();
        String team = saved.getTeam();
        Integer userID = saved.getUserID();

        if (JavaPostgreSql.checkPasswordTeam(password, teamID, userID)) {
            saved.setTeam(team);
            saved.setTeamID(teamID);

            Stage currentStage = (Stage) join_btn.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(true);
            stage.setTitle("Lan Party");
            stage.setScene(scene);
            stage.show();
        } else {
            pass.setText("");
            pass.setPromptText("Wrong password");
            pass.setAlignment(Pos.CENTER);
        }
    }

}


