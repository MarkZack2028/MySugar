package LoginPage;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import SignUpPage.PenggunaManager;
import SignUpPage.User;
import SignUpPage.UserData;
import javafx.scene.Node;
import CatatanHarian.LoginSession;

import java.io.IOException;

import Scene.OpenScene;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonDaftarSekarang;
    @FXML
    private Label errorLabel;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;


    @FXML
    private void handleLogin(ActionEvent event) {
        String uname = username.getText();
        String pass = password.getText();

        if (uname.isEmpty() || pass.isEmpty()) {
            showAlert("Semua field harus diisi!");
            return;
        }

        User loginUser = UserData.validateLogin(uname, pass);
        if (loginUser != null) {
            LoginSession.setCurrentUsername(uname);
            showAlertSukses("Success","Login berhasil, Selamat datang " + loginUser.getFirstName() + "!");
           try {
            keDashboard(event); // DIBUNGKUS try-catch
        } catch (Exception e) {
            e.printStackTrace();
            showAlertError("Error", "Gagal membuka dashboard.");
        }
        }else {
            showAlertError("Incorrect", "Username atau Password salah!");
        }
    }
    //method keDashboard
    //ini masih error
    @FXML
    private void keDashboard(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/DashboardSetelahLogin/DashboardSetelahLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //method signUp
    //ini masih error
    @FXML
    private void keSignUp(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/SignUpPage/SignUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //memberikan pop up ketika salah memasukkan user & pass
    private void showAlertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
         // Tambahkan style class
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
        alert.showAndWait();
    }
    private void showAlertSukses(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
         // Tambahkan style class
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
        alert.showAndWait();
    }
    private void showAlert(String pesan) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Informasi Login");
    alert.setHeaderText(null);
    alert.setContentText(pesan);
    alert.showAndWait();
}
}
