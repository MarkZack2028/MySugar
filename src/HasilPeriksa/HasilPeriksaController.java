package HasilPeriksa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HasilPeriksaController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
     @FXML
    private void backKePilihFitur(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/PilihFitur/PilihFitur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
