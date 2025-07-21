package Main;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
public class MySugar extends Application{
    public void start(Stage primaryStage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/DashboardSebelumLogin/DashboardSebelumLogin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/AksesAdmin/DashboardAdmin.fxml"));

        primaryStage.setTitle("MySugar");
        primaryStage.setScene(new Scene(root,1000,650));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }  
}