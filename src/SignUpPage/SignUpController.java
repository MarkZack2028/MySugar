package SignUpPage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class SignUpController implements Initializable{

    //nampung datanya
   private ArrayList<User> users = new ArrayList<>();
   

    @FXML
    private TextField username;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private AnchorPane AnchorPane;

    //multiScene
    @FXML
    private void keLogin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //pesan
    private void showAlert(String pesan) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Informasi");
    alert.setHeaderText(null);
    alert.setContentText(pesan);
    alert.showAndWait();
    }

    @FXML
    private void addUserGenderList() {
        System.out.println("Gender selected: " + gender.getValue());
    }

    @FXML
    private void buttonTambah(ActionEvent event){
        String uname = username.getText();
        String pass = password.getText();
        String phone = phoneNumber.getText();
        String fname = firstName.getText();
        String lname = lastName.getText();
        String gend = gender.getValue();

        if (uname.isEmpty() || pass.isEmpty() || phone.isEmpty() || fname.isEmpty() || lname.isEmpty() || gend == null) {
            showAlert("Semua data harus diisi!");
            return;
        }

        User user = new User(uname, pass, fname, lname, gend, phone);
        users.add(user);
        //ini yang diubah
        ArrayList<User> existingUsers = UserData.loadUsersFromXML();
        UserData.saveUsersToXML(users);
        //debugging
        System.out.println("TOMBOL DITEKAN");
        showAlert("User berhasil ditambahkan!");

        clearForm();

        // langsung pindah ke halaman login setelah sukses
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        username.clear(); password.clear(); phoneNumber.clear();
        firstName.clear(); lastName.clear(); gender.getSelectionModel().clearSelection();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.getItems().addAll("Male", "Female");
         users = UserData.loadUsersFromXML(); // load data XML saat awal
        // Nama.setCellValueFactory(new PropertyValueFactory<>("username"));
        // passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        // Kontak.setCellValueFactory(new PropertyValueFactory<>("kontak"));
    }
}