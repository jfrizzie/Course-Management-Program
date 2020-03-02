package ist361;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julia
 */
public class NavigationController implements Initializable {

    
    @FXML
    protected void viewSchedule(ActionEvent event) throws IOException {
        try {
            Stage stageOriginal = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageOriginal.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleUI.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("View Current Schedule");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void enrollCourses(ActionEvent event) throws IOException {

    }

    @FXML
    protected void manageAccount(ActionEvent event) throws IOException {

    }

    @FXML
    protected void dropCourses(ActionEvent event) throws IOException {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
