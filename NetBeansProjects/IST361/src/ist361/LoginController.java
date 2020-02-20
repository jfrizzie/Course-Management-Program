package ist361;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author rqe5116
 */
public class LoginController implements Initializable {
    
    private UserList users;
    @FXML private Text actiontarget;
    @FXML private TextField userField;
    @FXML private PasswordField passwordField;
    
    public LoginController() {
        users = new UserList();
        try {
            FileReader reader = new FileReader("UserInfo.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line, username, password;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                username=line.substring(0,(line.lastIndexOf(":")));
                password=line.substring((line.lastIndexOf(":")+1),line.length());
                System.out.println(username + " " + password);
                users.getUsers().add(new User(username, password));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }      
    }
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        actiontarget.setText("Sign in button pressed");
        loginAction();
        
    }
    @FXML protected void handleCreateNewUserButtonAction(ActionEvent event) throws IOException {
        actiontarget.setText("Create New User button pressed");
         try {
                Stage stageOriginal = (Stage) userField.getScene().getWindow();
                stageOriginal.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateNewUserUI.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();

                stage.setTitle("RESPAID Main Menu");
                stage.setScene(new Scene(root1));  
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        
    }
    @FXML protected void onEnter(ActionEvent event) throws IOException {
        actiontarget.setText("Enter button pressed");
        loginAction();
        
    }
    @FXML protected void cancelButtonAction(ActionEvent event) {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private boolean authenticate(String username, String password) {
        return (users.find(username) != null & users.find(username).authenticate(password));
    }
    private void loginAction()
    {
        if(authenticate(userField.getText(), passwordField.getText()))
        {
            actiontarget.setText("Authenticated");
            try {
                Stage stageOriginal = (Stage) userField.getScene().getWindow();
                stageOriginal.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NavigationUI.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();

                stage.setTitle("RESPAID Main Menu");
                stage.setScene(new Scene(root1));  
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            actiontarget.setText("Invalid Credentials");
        }
    }
}
