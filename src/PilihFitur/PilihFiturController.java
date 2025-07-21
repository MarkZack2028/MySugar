package PilihFitur;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class PilihFiturController {
    @FXML
    private Button buttonHubungi;
    @FXML
    private Button buttonUgd;
    @FXML
    private Button buttonHasil;
    @FXML
    private Button buttonBuatJadwal;
    @FXML
    private Button buttonBack;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private void kePendaftaran(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/PesanHasil/Pemesanan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void keHubungiDokter(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/HubungiDokter/hubungidokter.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backDiPilihFitur(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/DashboardSetelahLogin/DashboardSetelahLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void keHasilPemeriksaan(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/HasilPeriksa/HasilPeriksa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // ✅ Tambahkan method untuk tombol UGD Emergency
    @FXML
    private void ugdEmergency(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Form Darurat - UGD");
    dialog.setHeaderText("Silakan isi alasan keadaan darurat dan pilih rumah sakit tujuan.");

    // Tombol OK dan Cancel
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    // --- Komponen Form ---
    Label labelKeterangan = new Label("Alasan/Keterangan:");
    TextArea inputKeterangan = new TextArea();
    inputKeterangan.setPromptText("Contoh: Gula darah naik drastis, pingsan, dll...");
    inputKeterangan.setWrapText(true);
    inputKeterangan.setPrefRowCount(3);

    Label labelRS = new Label("Pilih Rumah Sakit:");
    ComboBox<String> comboRS = new ComboBox<>();
    comboRS.getItems().addAll(
        "RSU Harapan Sehat (1.2 km)",
        "RSUD Kota Medika (2.5 km)",
        "RS MMC Emergency (3.0 km)"
    );
    comboRS.setPromptText("Pilih salah satu...");

    // Layout isi dialog
    GridPane grid = new GridPane();
    grid.setVgap(10);
    grid.setHgap(10);
    grid.setStyle("-fx-padding: 20px;");

    grid.add(labelKeterangan, 0, 0);
    grid.add(inputKeterangan, 0, 1);
    grid.add(labelRS, 0, 2);
    grid.add(comboRS, 0, 3);

    dialog.getDialogPane().setContent(grid);

    // Tangani tombol OK
    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        String alasan = inputKeterangan.getText().trim();
        String rs = comboRS.getValue();

        if (alasan.isEmpty() || rs == null) {
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            alertWarning.setTitle("Input Tidak Lengkap");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Mohon isi keterangan dan pilih rumah sakit.");
            alertWarning.showAndWait();
        } else {
            // ✅ Simulasi data berhasil terkirim
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("Data Terkirim");
            alertSuccess.setHeaderText("Ambulans dalam perjalanan!");
            alertSuccess.setContentText("📋 Keterangan: " + alasan + "\n📍 Rumah Sakit: " + rs);
            alertSuccess.showAndWait();

            // TODO: Simpan ke XML di sini nanti
        }
    }
}

}
