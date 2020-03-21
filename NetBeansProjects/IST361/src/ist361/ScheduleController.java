package ist361;

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
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScheduleController implements Initializable{
    
    @FXML
    private ListView myCourses;
    MyCourses courseClass;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //TODO
         myCourses.setItems(courseClass.getCourses());
    }
    
    
    @FXML protected void backButton(ActionEvent event) throws IOException{
        try {
            Stage stageOriginal = (Stage) ((Node) event.getSource()).getScene().getWindow();;
            stageOriginal.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NavigationUI.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Main Menu");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    

    
}