package CatatanHarian;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GrafikKaloriController implements Initializable {

    @FXML
    private BarChart<String, Number> calories_barchart;
    @FXML
    private Button calories_backbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tampilkanGrafikKalori();
    }
     @FXML
    private void handleBackButton() {
        Stage stage = (Stage) calories_backbtn.getScene().getWindow();
        stage.close();
    }

    private void tampilkanGrafikKalori() {
        List<CatatanHarianData> semuaData = CatatanXMLHandler.bacaDariXML();
        Map<String, Integer> totalKaloriPerHari = new TreeMap<>();

        for (CatatanHarianData data : semuaData) {
            String tanggal = data.getTanggal(); // format "20 July 2025" atau lainnya
            int totalKalori = data.getTotalKalori();

            totalKaloriPerHari.put(tanggal,
                totalKaloriPerHari.getOrDefault(tanggal, 0) + totalKalori);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Kalori per Hari");

        for (Map.Entry<String, Integer> entry : totalKaloriPerHari.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        calories_barchart.getData().add(series);
    }
}
