package PesanHasil;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;

public class PesanHasilController implements Initializable {
    @FXML
    private TextField NamaLengkap;
    @FXML
    private TextField TTL;
    @FXML
    private TextField Alamat;
    @FXML
    private TextField nomorTelepon;
    @FXML
    private TextArea keluhanPasien;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label label;
    @FXML
    private Button buttonBack;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private ComboBox<String> pilihRumahSakit;
    @FXML
    private ComboBox<String> pilihDokter;

    @FXML
    private void backKePilihFitur(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/PilihFitur/PilihFitur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //buttonSubmit
    @FXML
    private void handleSubmit(ActionEvent event) {
    String nama = NamaLengkap.getText();
    String ttl = TTL.getText();
    String alamat = Alamat.getText();
    String noTelp = nomorTelepon.getText();
    String gender = male.isSelected() ? "Male" : "Female";

    Pendaftaran data = new Pendaftaran(nama, ttl, alamat, noTelp, gender);
    PendaftaranManager.tambahData(data);

    // Simpan ke XML
    PendaftaranXMLHandler.simpanKeXML(PendaftaranManager.getAllData());

    // Optional: alert
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText("Pendaftaran berhasil disimpan!");
    alert.showAndWait();
}

    // Data relasi rumah sakit dan dokter
    private final Map<String, List<String>> rumahSakitToDokter = new HashMap<>();

    // Placeholder default text
    private final String defaultNama = "Nama Lengkap";
    private final String defaultAlamat = "Alamat";
    private final String defaultTTL = "Tempat tanggal lahir";
    private final String defaultTelepon = "Nomor Telepon";
    private final String defaultKeluhan = "Tulis keluhan anda...";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupPlaceholderBehavior(NamaLengkap, defaultNama);
        setupPlaceholderBehavior(Alamat, defaultAlamat);
        setupPlaceholderBehavior(TTL, defaultTTL);
        setupPlaceholderBehavior(nomorTelepon, defaultTelepon);
        setupPlaceholderBehavior(keluhanPasien, defaultKeluhan);

        //combo box
        rumahSakitToDokter.put("RS Umum Sehat Sentosa", Arrays.asList("dr. Andi Wijaya", "dr. Lina Marlina"));
        rumahSakitToDokter.put("RS Mitra Keluarga", Arrays.asList("dr. Siti Rohmah", "dr. Budi Santoso"));
        rumahSakitToDokter.put("RS Harapan Bunda", Arrays.asList("dr. Dedi Sutrisno", "dr. Maria Farida"));

        pilihRumahSakit.getItems().addAll(rumahSakitToDokter.keySet());

        // Event ketika rumah sakit dipilih
        pilihRumahSakit.setOnAction(event -> {
            String selectedRS = pilihRumahSakit.getValue();
            List<String> dokterList = rumahSakitToDokter.getOrDefault(selectedRS, new ArrayList<>());

            pilihDokter.getItems().clear();
            pilihDokter.getItems().addAll(dokterList);
            pilihDokter.setValue(null); // reset pilihan dokter
        });
    }

    //efek textfield
    private void setupPlaceholderBehavior(TextField field, String placeholder) {
        field.setText(placeholder);
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                // Saat fokus: kosongkan jika sama dengan default
                if (field.getText().equals(placeholder)) {
                    field.clear();
                }
            } else {
                // Saat tidak fokus: kembalikan default jika kosong
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                }
            }
        });
    }

    //efek textarea
    private void setupPlaceholderBehavior(TextArea area, String placeholder) {
        area.setText(placeholder);
        area.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                if (area.getText().equals(placeholder)) {
                    area.clear();
                }
            } else {
                if (area.getText().isEmpty()) {
                    area.setText(placeholder);
                }
            }
        });
    }

    //radioButton
    @FXML
    private void klikRadioButton(ActionEvent event) {
        RadioButton selected = (RadioButton) event.getSource();
        String gender = selected.getText(); // langsung ambil teks "Male" atau "Female"
        //label.setText(gender);
    }

    @FXML
    public void initialize() {
        // Tidak perlu inisialisasi tambahan jika hanya ambil teks saat klik
    }

}

