package AksesAdmin;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.layout.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import CatatanHarian.CatatanHarianData;
import CatatanHarian.CatatanXMLHandler;
import PesanHasil.Pendaftaran;
import PesanHasil.PendaftaranManager;
import SignUpPage.User;
import SignUpPage.UserData;
import CatatanHarian.LoginSession;

public class AdminController {

    @FXML
    private Button close, minimize, home_btn,addUser_btn, registration_btn,logout;

    @FXML
    private TextField total_user,total_male,total_female;

    @FXML
    private AnchorPane home_form,addUser_form, registration , main_form , foodDiary;

    @FXML
    private BarChart<String, Number> home_chart;

    // Add Employee Section
    @FXML
    private TableView<User> addUser_tableView;

    @FXML
    private TableColumn<User, String> addUser_col_password, addUser_col_userName,
            addUser_col_firstName, addUser_col_lastName, addUser_col_Gender,
            addUser_col_phone;

    @FXML
    private TextField password,addPassword, addFirstName,
           addLastName, addPhone,usernameUser;

    @FXML
    private ComboBox<String> addGender, addEmployee_position;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn, addUser_addBtn, addUser_updateBtn,
            addUser_deleteBtn, addUser_clearBtn;

    // PENDAFTARAN
    @FXML
    private TextField NamaLengkapUser;

    @FXML
    private ComboBox genderUser;

    @FXML
    private TextField TTLUser, alamatUser, nomorTeleponUser;

    @FXML
    private TableView<Pendaftaran> user_tableView;
    private ObservableList<Pendaftaran> dataPendaftaran;

    @FXML
    private TableColumn<Pendaftaran,String> nama_col_user, TTL_col, alamat_col,
            nomortelepon_col, gender_col;
    @FXML
    private Button user_updateBtn, user_clearBtn;

    private ObservableList<User> userList;

    //foodDiary
    @FXML 
    private TextField makanMalam_food, makanSiang_food , makanPagi_food;

    @FXML
    private TableView<CatatanHarianData> fooddiary_tableView;
    private ObservableList<CatatanHarianData> foodDataList;

    @FXML
    private TableColumn<CatatanHarianData,String> makanmalam_col , makanpagi_col , makansiang_col,foodDiary_usernameCol;

    @FXML
    private Button foodDiary_btn , food_deletebtn , food_updatebtn;


    // === Event Handlers ===
    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void minimize() {
        // Diimplementasikan jika menggunakan custom stage control
    }

    @FXML
    private void logout() {
        System.out.println("Logout clicked.");
        // Tambahkan logika untuk logout
    }

    //HOME FORM
    private void loadGenderChart() {
        int maleCount = 0;
        int femaleCount = 0;

        // Ambil semua data user dari XML
        List<User> allUsers = UserData.getAllUsers(); // Pastikan method ini ada

        for (User user : allUsers) {
            String gender = user.getGender();
            if (gender.equalsIgnoreCase("Male")) {
                maleCount++;
            } else if (gender.equalsIgnoreCase("Female")) {
                femaleCount++;
            }
        }

        // Buat series untuk chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Gender");

        series.getData().add(new XYChart.Data<>("Male", maleCount));
        series.getData().add(new XYChart.Data<>("Female", femaleCount));

        home_chart.getData().clear();     // Bersihkan data lama
        home_chart.getData().add(series); // Tampilkan data baru
    }
    private void loadGenderStatistics() {
        ArrayList<User> users = UserData.loadUsersFromXML(); // Ambil semua data user dari XML

        int maleCount = 0;
        int femaleCount = 0;

        for (User user : users) {
            String gender = user.getGender();
            if (gender != null) {
                if (gender.equalsIgnoreCase("Male")) {
                    maleCount++;
                } else if (gender.equalsIgnoreCase("Female")) {
                    femaleCount++;
                }
            }
        }

        int totalCount = users.size();

        total_user.setText(String.valueOf(totalCount));
        total_male.setText(String.valueOf(maleCount));
        total_female.setText(String.valueOf(femaleCount));
    }

    @FXML
    private void switchForm(javafx.event.ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
           addUser_form.setVisible(false);
            registration.setVisible(false);
            foodDiary.setVisible(false);
        } else if (event.getSource() ==addUser_btn) {
            home_form.setVisible(false);
           addUser_form.setVisible(true);
            registration.setVisible(false);
            foodDiary.setVisible(false);
        } else if (event.getSource() == registration_btn) {
            home_form.setVisible(false);
           addUser_form.setVisible(false);
            registration.setVisible(true);
            foodDiary.setVisible(false);
        } else if (event.getSource() == foodDiary_btn) {
            home_form.setVisible(false);
           addUser_form.setVisible(false);
            registration.setVisible(true);
            foodDiary.setVisible(true);

        } 
    }

    //ADD USER
    @FXML
    private void addEmployeeSearch() {
        System.out.println("Search triggered.");
        // Tambahkan logika pencarian
    }

    @FXML
    private void addEmployeeSelect(MouseEvent event) {
        System.out.println("User selected.");
        // Tambahkan logika pemilihan data dari tabel
    }

    @FXML
    private void addEmployeeInsertImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            addEmployee_image.setImage(image);
        }
    }

    @FXML
    private void addEmployeeAdd() {
        String username = usernameUser.getText();
        String passwordVal = addPassword.getText();
        String firstName = addFirstName.getText();
        String lastName = addLastName.getText();
        String phone = addPhone.getText();
        String gender = addGender.getValue();

        if (username.isEmpty() || passwordVal.isEmpty() || firstName.isEmpty()
                || lastName.isEmpty() || phone.isEmpty() || gender == null) {
            showAlert("Peringatan", "Silakan lengkapi semua data.", Alert.AlertType.WARNING);
            return;
        }

        User user = new User(username, passwordVal, firstName, lastName, gender, phone);
        userList.add(user); // Tambah ke TableView
        UserData.saveUsersToXML(new ArrayList<>(userList)); // Simpan ke XML
        addEmployeeReset(); // Reset input
    }

    @FXML
    private void addEmployeeUpdate() {
        User selectedUser = addUser_tableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Peringatan", "Pilih data yang ingin diubah.", Alert.AlertType.WARNING);
            return;
        }
        selectedUser.setUsername(usernameUser.getText());
        selectedUser.setPassword(addPassword.getText());
        selectedUser.setFirstName(addFirstName.getText());
        selectedUser.setLastName(addLastName.getText());
        selectedUser.setPhoneNumber(addPhone.getText());
        selectedUser.setGender(addGender.getValue());

        addUser_tableView.refresh(); // Refresh tabel
        UserData.saveUsersToXML(new ArrayList<>(userList)); // Simpan ke XML
        addEmployeeReset();
    }

    @FXML
    private void addEmployeeDelete() {
        User selectedUser = addUser_tableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Peringatan", "Pilih data yang ingin dihapus.", Alert.AlertType.WARNING);
            return;
        }

        userList.remove(selectedUser);
        UserData.saveUsersToXML(new ArrayList<>(userList));
        addEmployeeReset();
    }

    @FXML
    private void addEmployeeReset() {
        addPassword.clear();
        addFirstName.clear();
        addLastName.clear();
        addPhone.clear();
        addGender.getSelectionModel().clearSelection();
        usernameUser.clear();
    }

    @FXML
    private void addEmployeeGendernList() {
        System.out.println("Gender selected: " + addGender.getValue());
    }

    
    //REGISTRATION
    @FXML
    private void salarySelect(MouseEvent event) {
        System.out.println("row selected.");
        // Logika pemilihan baris gaji
    }

    //show allert
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //TABLEVIEW REGISTRATION
    @FXML
    private void registrationUpdate() {
        Pendaftaran selected = user_tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Peringatan", "Pilih data yang ingin diperbarui.", Alert.AlertType.WARNING);
            return;
        }

        selected.setNamaLengkap(NamaLengkapUser.getText());
        selected.setTtl(TTLUser.getText());
        selected.setAlamat(alamatUser.getText());
        selected.setNomorTelepon(nomorTeleponUser.getText());
        selected.setGender(genderUser.getValue().toString());

        user_tableView.refresh();
        PendaftaranManager.saveToXML();
        registrationClear();
    }
    @FXML
    private void deleteRegistration(){
        Pendaftaran selectedPendaftaran = user_tableView.getSelectionModel().getSelectedItem();
        if (selectedPendaftaran == null) {
            showAlert("Peringatan", "Pilih data pendaftaran yang ingin dihapus.", Alert.AlertType.WARNING);
            return;
        }

        // Hapus dari list utama PendaftaranManager
        PendaftaranManager.getAllData().remove(selectedPendaftaran);

        // Hapus dari ObservableList (tampilan TableView)
        dataPendaftaran.remove(selectedPendaftaran);

        // Simpan ulang ke file XML
        PendaftaranManager.saveToXML();

        // Reset form
        resetRegistrationForm();
    }

    @FXML
    private void registrationClear() {
        NamaLengkapUser.clear();
        TTLUser.clear();
        alamatUser.clear();
        nomorTeleponUser.clear();
        genderUser.getSelectionModel().clearSelection();
        user_tableView.getSelectionModel().clearSelection();
    }
    private void resetRegistrationForm() {
        NamaLengkapUser.clear();
        TTLUser.clear();
        alamatUser.clear();
        nomorTeleponUser.clear();
        genderUser.setValue(null);
    }

    @FXML
    private void registrationSelect(MouseEvent event) {
        Pendaftaran selected = user_tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            NamaLengkapUser.setText(selected.getNamaLengkap());
            TTLUser.setText(selected.getTtl());
            alamatUser.setText(selected.getAlamat());
            nomorTeleponUser.setText(selected.getNomorTelepon());
            genderUser.setValue(selected.getGender());
        }
    }
    

    //CATATAN HARIAN
    private void tampilkanDatakeTable() {
        ArrayList<CatatanHarianData> list = CatatanXMLHandler.bacaDariXML();
        fooddiary_tableView.getItems().setAll(list);
    }
    
    @FXML
    private void food_updatebtn(){
        CatatanHarianData selected = fooddiary_tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Misalnya kita ubah makan pagi menjadi "Roti" (contoh)
            selected.setMakanPagi(makanPagi_food.getText());
            selected.setMakanSiang(makanSiang_food.getText());
            selected.setMakanMalam(makanMalam_food.getText());
            // Update TableView
            fooddiary_tableView.refresh();

            // Simpan ulang ke XML
            CatatanXMLHandler.simpanKeXML(new ArrayList<>(foodDataList));
            System.out.println("Data berhasil diupdate.");
        } else {
            System.out.println("Pilih data yang ingin diupdate.");
        }
    }
    @FXML
    private void food_deletebtn(){
        CatatanHarianData selected = fooddiary_tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            foodDataList.remove(selected);
            fooddiary_tableView.setItems(foodDataList); // refresh TableView

            // Simpan ulang ke XML
            CatatanXMLHandler.simpanKeXML(new ArrayList<>(foodDataList));
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Pilih data yang ingin dihapus.");
        }
    }

    private void loadDataPendaftaran() {

        ObservableList<Pendaftaran> list = FXCollections.observableArrayList(PendaftaranManager.getAllData());
        user_tableView.setItems(list);
    }
    @FXML
    public void initialize() {
        
        //load gender
        addGender.getItems().addAll("Male", "Female");
        //page homeform
        loadGenderChart();
        loadGenderStatistics();
        userList = FXCollections.observableArrayList(UserData.loadUsersFromXML());

        addUser_col_userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        addUser_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        addUser_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addUser_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addUser_col_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addUser_col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        // 🟢 INI WAJIB ADA supaya tampil
        addUser_tableView.setItems(userList);

        //data pendaftaran
        genderUser.getItems().addAll("Male", "Female");
        nama_col_user.setCellValueFactory(new PropertyValueFactory<>("namaLengkap"));
        TTL_col.setCellValueFactory(new PropertyValueFactory<>("ttl"));
        alamat_col.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        nomortelepon_col.setCellValueFactory(new PropertyValueFactory<>("nomorTelepon"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // Load data dari XML
        PendaftaranManager.loadFromXML();
        // Tampilkan di TableView
        dataPendaftaran = FXCollections.observableArrayList(PendaftaranManager.getAllData());
        user_tableView.setItems(dataPendaftaran);

        //DATA CATATAN HARIAN
        foodDiary_usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        makanpagi_col.setCellValueFactory(new PropertyValueFactory<>("makanPagi"));
        makansiang_col.setCellValueFactory(new PropertyValueFactory<>("makanSiang"));
        makanmalam_col.setCellValueFactory(new PropertyValueFactory<>("makanMalam"));
        foodDataList = FXCollections.observableArrayList(CatatanXMLHandler.bacaDariXML());
        fooddiary_tableView.setItems(foodDataList);
        tampilkanDatakeTable();
    }

    private void loadChartData() {
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Employees");
        series.getData().add(new XYChart.Data<>("Active", 15));
        series.getData().add(new XYChart.Data<>("Inactive", 5));
        series.getData().add(new XYChart.Data<>("Present", 10));

        home_chart.getData().add(series);
    }
}
