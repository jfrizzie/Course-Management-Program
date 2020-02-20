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
public class CreateNewUserController implements Initializable {
    
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmNewPasswordField;
    @FXML private TextField userField;
    @FXML private Text actiontarget;
    
    @FXML protected void handleCreateUserButtonAction(ActionEvent event) throws IOException {
        if(newPasswordField.getText().equals(confirmNewPasswordField.getText())){
            File File = new File("UserInfo.txt");
            File tempFile = new File("tempUserInfo.txt");
 
            BufferedReader reader = new BufferedReader(new FileReader(File));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
 
            String lineToUpdate = userField.getText();
            String currentLine;
        
            boolean validCredentials = true;
 
            while((currentLine = reader.readLine()) != null) {
 
                String trimmedLine = currentLine.trim();
   
                if(trimmedLine.contains(userField.getText())){
                    actiontarget.setText("Username Already Exists");
                    validCredentials=false;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            if(validCredentials)
            {
                actiontarget.setText("User Successfully Added");
                writer.write(userField.getText()+":"+newPasswordField.getText()+System.getProperty("line.separator"));
                writer.close(); 
                reader.close(); 
                File.delete();
                tempFile.renameTo(File);
                try {
                    Stage stageOriginal = (Stage) actiontarget.getScene().getWindow();
                    stageOriginal.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
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
        }
        else{
            actiontarget.setText("The Password Fields Don't Match");
        }
    }
     @FXML protected void handleCancelButtonAction(ActionEvent event) {
        try {
            Stage stageOriginal = (Stage) actiontarget.getScene().getWindow();
            stageOriginal.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("RESPAID");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
