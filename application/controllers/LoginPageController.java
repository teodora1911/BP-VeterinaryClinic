package application.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button submitButton;
    @FXML private Label bannerLabel;
    @FXML private CheckBox administratorCheck;

    @FXML
    protected void submitButtonAction(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(!username.isEmpty() && !password.isEmpty()){
            URL url = getClass().getClassLoader().getResource("application" + File.separator + "fxmlfiles" + File.separator + "AdministratorStartPage.fxml");
            if(url != null){
                FXMLLoader loader = new FXMLLoader(url);
                AdministratorStartPageController contoller = new AdministratorStartPageController();
                loader.setController(contoller);
                try{
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        } else {
            bannerLabel.setVisible(true);
        }
    }

    private Stage stage;

    public LoginPageController(Stage stage){
        this.stage = stage;
    }
}
