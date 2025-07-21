package CatatanHarian;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;

public class CatatanHarian{

    @FXML
    private Button buttonKeluar;
    @FXML
    private Button buttonDownloadAplikasi;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Button buttonBack;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private BarChart calories_barchart;

    @FXML
    private void keluar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
    @FXML
    private void keDashboard(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/DashboardSetelahLogin/DashboardSetelahLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showPopup(String tanggal) {
        try {
            // Load FXML popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CatatanHarian/pilihmenu.fxml"));
            DialogPane popupContent = loader.load();

            // Optional: akses controller popup
            PilihMenuMakan controller = loader.getController();
            controller.setTanggalDipilih(tanggal); // Contoh

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(popupContent);
            dialog.initModality(Modality.APPLICATION_MODAL); // Block input ke jendela lain
            dialog.initOwner(rootpane.getScene().getWindow()); // Pastikan referensinya benar
            dialog.setTitle("Pilih Menu Makanan");
            Optional<ButtonType> result = dialog.showAndWait();
            result.ifPresent(response -> {
                if (response.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                controller.simpanCatatan(); // aman
            }
        });
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
    //GRAFIK
    @FXML
    private void lihatGrafik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CatatanHarian/grafikmakanan.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Grafik Kalori Mingguan");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    private List<Makanan> daftarMakanan = Arrays.asList(
        new Makanan("Nasi Goreng", 350),
        new Makanan("Ayam Bakar", 250),
        new Makanan("Sate", 300),
        new Makanan("Roti", 150),
        new Makanan("Susu", 120)
        // dan seterusnya
    );
    //
    @FXML
    private void handleKlikRabu(MouseEvent event){
        showPopup("20 July 2025");
    }
    @FXML
    private void handleKlikSelasa(MouseEvent event){
        showPopup("19 July 2025");
    }
    @FXML
    private void handleKlikSenin(MouseEvent event){
        showPopup("18 July 2025");
    }
    @FXML
    private void handleKlikMinggu(MouseEvent event){
        showPopup("17 July 2025");
    }
    @FXML
    private void handleKlikSabtu(MouseEvent event){
        showPopup("16 July 2025");
    }
    @FXML
    private void handleKlikJumat(MouseEvent event){
        showPopup("15 July 2025");
    }
    @FXML
    private void handleKlikKamis(MouseEvent event){
        showPopup("14 July 2025");
    }
    @FXML
    private void handleKlikRabu13(MouseEvent event){
        showPopup("13 July 2025");
    }
    @FXML
    private void handleKlikSelasa12(MouseEvent event){
        showPopup("12 July 2025");
    }
    @FXML
    private void handleKlikSenin11(MouseEvent event){
        showPopup("11 July 2025");
    }
    @FXML
    private void handleKlikMinggu10(MouseEvent event){
        showPopup("10 July 2025");
    }
}