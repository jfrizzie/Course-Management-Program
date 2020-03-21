package ist361;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julia
 */
public class EnrollCourseController implements Initializable {

    private ObservableList<String> observe = FXCollections.observableArrayList();
    @FXML
    private ListView courseList;
    @FXML
    private ListView myCourses;
    private ObservableList<String> tempCourses = FXCollections.observableArrayList();
    MyCourses courseClass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            FileReader reader = new FileReader("courselist.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                observe.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        courseList.setItems(observe);
    }

    public EnrollCourseController() {
        
    }

    @FXML
    protected void addtoCourseList(ActionEvent event) throws IOException {
        try {
            String selectedText = courseList.getSelectionModel().getSelectedItem().toString();
            tempCourses.add(selectedText);
            myCourses.setItems(tempCourses);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void EnrollSelectedCourses(ActionEvent event) throws IOException {
        try {
            for (int i = 0; i < tempCourses.size(); i++) {
                courseClass.addCourse(tempCourses.get(i));
            }
            tempCourses.clear();
            myCourses.setItems(tempCourses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    @FXML
    protected void backButton(ActionEvent event) throws IOException {
        try {
            Stage stageOriginal = (Stage) ((Node) event.getSource()).getScene().getWindow();;
            stageOriginal.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NavigationUI.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Main Menu");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the observe
     */
    public ObservableList<String> getObserve() {
        return observe;
    }

    /**
     * @param observe the observe to set
     */
    public void setObserve(ObservableList<String> observe) {
        this.observe = observe;
    }

}
