package DashboardSetelahLogin;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DashboardSetelahLogin {

    @FXML
    private Button buttonKeluar;
    @FXML
    private Button buttonDownloadAplikasi;
    @FXML
    private Button buttonCatatanHarian;
    @FXML
    private Button buttonPesanHasil;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private void keluar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void keCatatanHarian(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/CatatanHarian/catatanharian.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void kePesanHasil(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/PilihFitur/PilihFitur.fxml"));
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
        dialogPane.getStylesheets().add(getClass().getResource("DashboardSetelahLogin.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
        alert.showAndWait();
    }
    @FXML
    private void handleButtonDownload(){
        showAlert("Tidak Tersedia", "Aplikasi Belum Tersedia");
    }
}
//..\resources\LogoCatatanHarian.png