package DashboardSebelumLogin;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Main.MySugar;
import Scene.OpenScene;
import javafx.scene.Node;

public class DashboardSebelumLogin {

    @FXML
    private Button buttonMasuk;
    @FXML
    private Button buttonDownloadAplikasi;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Button buttonStripTiga;
    //onAction fxml diubah
    //

    //method untuk masuk ke login
    @FXML
    private void keLogin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //button download
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        // Tambahkan style class
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("DashboardSebelumLogin.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");

        alert.showAndWait();
    }

    @FXML
    private void handleButtonDownload(){
        showAlert("Tidak Tersedia", "Aplikasi Belum Tersedia");
    }
}