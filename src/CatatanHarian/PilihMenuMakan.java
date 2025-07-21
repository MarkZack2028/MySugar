package CatatanHarian;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import CatatanHarian.LoginSession;

public class PilihMenuMakan{
    @FXML
    private DatePicker tanggalPicker;
    @FXML
    private ComboBox<String> comboPagi;
    @FXML
    private ComboBox<String> comboSiang;
    @FXML
    private ComboBox<String> comboMalam;
    @FXML
    private ButtonType btnSimpan;
    @FXML
    private ButtonType btnBatal;

    @FXML
    private Label labelTanggal;

    public void setTanggalDipilih(String tanggal) {
        labelTanggal.setText(tanggal);
    }
    public void simpanCatatan() {
        String tanggal = labelTanggal.getText(); // atau ambil dari DatePicker
        String pagi = comboPagi.getValue();
        String siang = comboSiang.getValue();
        String malam = comboMalam.getValue();

        String username = LoginSession.getCurrentUsername(); // Ambil username dari sesi login
            if (username == null || username.isEmpty()) {
            System.out.println("Username tidak ditemukan di sesi login.");
            return;
        }
        CatatanHarianData data = new CatatanHarianData(username,pagi,siang,malam,tanggal);
        ArrayList<CatatanHarianData> dataList = CatatanXMLHandler.bacaDariXML();
        dataList.add(data);
        CatatanXMLHandler.simpanKeXML(dataList);
    }
    @FXML
    private void handleSimpan(ActionEvent event) {
        simpanCatatan();
    }


    @FXML
    public void initialize() {
        comboPagi.getItems().addAll("Nasi Uduk", "Oatmeal", "Telur Rebus");
        comboSiang.getItems().addAll("Ayam Bakar", "Sop Sayur", "Nasi Goreng");
        comboMalam.getItems().addAll("Salad", "Roti Gandum", "Sup Ayam");
    }
}
