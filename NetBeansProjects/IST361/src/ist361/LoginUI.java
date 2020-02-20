/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist361;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;


// edodoo: this is a git clone test.
/**
 *
 * @author rqe5116
 */
public class LoginUI extends Application {
      
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
    
        Scene scene = new Scene(root, 300, 275);
        //LoginController LoginController = new LoginController(scene);
    
        stage.setTitle("IST 361 Application");
        stage.setScene(scene);
        stage.show();
    }
  
}
