
package ist361;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Julia
 */
public class MainAccountController implements Initializable{
    
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmNewPasswordField;
    @FXML private TextField userField;
    @FXML private TextField usernameField;
    @FXML private Text actiontarget;
    
    @FXML protected void handleUpdatePasswordButtonAction(ActionEvent event) throws IOException {
        if(newPasswordField.getText().equals(confirmNewPasswordField.getText())){
            File File = new File("UserInfo.txt");
            File tempFile = new File("tempUserInfo.txt");
 
            BufferedReader reader = new BufferedReader(new FileReader(File));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
 
            String lineToUpdate = userField.getText();
            String currentLine;
        
            boolean validCredentials = false;
 
            while((currentLine = reader.readLine()) != null) {
 
                String trimmedLine = currentLine.trim();
   
                if(trimmedLine.equals((userField.getText()+":"+oldPasswordField.getText()).trim())){
                    actiontarget.setText("Password Updated");
                    System.out.println("Password Updated");
                    validCredentials=true;
                    writer.write(userField.getText()+":"+newPasswordField.getText()+System.getProperty("line.separator"));
                }
                else{
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            if(!validCredentials)
            {
                actiontarget.setText("Unable to Update Password. Invalid Credentials");
            }
            else{
                reader.close();
                writer.close();
                try
                {
                    InputStream is = new FileInputStream(tempFile);
                    OutputStream os = new FileOutputStream(File);
                    byte[] buffer = new byte[1024];
                    int length;
                    while((length=is.read(buffer))>0)
                    {
                        os.write(buffer,0,length);
                    }
                    is.close();
                    os.close();
                }
                finally{}
                
                
                try {
                    Stage stageOriginal = (Stage) actiontarget.getScene().getWindow();
                    stageOriginal.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();

                    stage.setTitle("IST 361 Application");
                    stage.setScene(new Scene(root1));  
                    stage.show();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            actiontarget.setText("The Update Password Fields Don't Match");
        }
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}
