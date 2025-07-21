package Scene;
import java.net.URL;
import Main.MySugar;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class OpenScene {
    private Pane halaman;
    
    public Pane getPane(String fileName){
        try{
            URL fileHalaman=MySugar.class.getResource(fileName+".fxml");
            //System.out.println(fileHalaman);
            
            if(fileHalaman==null){
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }
            
            new FXMLLoader();
            halaman = FXMLLoader.load(fileHalaman);
            System.out.println(fileHalaman);

            
        }catch (Exception e){
       
            System.out.println("Tidak ditemukan halaman tersebut");
        }
        
        return halaman;
    }
}