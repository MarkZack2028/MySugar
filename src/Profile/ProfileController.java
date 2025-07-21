package Profile;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML private TextField namaField;
    @FXML private TextField teleponField;
    @FXML private TextField alamatField;
    @FXML private TextField ttlField;

    @FXML
    private void handleSimpan() {
        String nama = namaField.getText();
        String telepon = teleponField.getText();
        String alamat = alamatField.getText();
        String ttl = ttlField.getText();

        System.out.println("Data disimpan:");
        System.out.println("Nama: " + nama);
        System.out.println("Telepon: " + telepon);
        System.out.println("Alamat: " + alamat);
        System.out.println("TTL: " + ttl);
    }
}
