package application.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController implements Initializable {

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
            
        } else {
            bannerLabel.setVisible(true);
        }
    }

    private Stage stage;
    private static final String RESOURCE = "application" + File.separator + "resources" + File.separator + "LoginForm.fxml";
    private static final String TITLE = "Prijava";

    public LoginFormController(Stage stage){
        this.stage = stage;
        try{
            URL url = getClass().getClassLoader().getResource(RESOURCE);
            if(url != null){
                FXMLLoader loader = new FXMLLoader(url);
                loader.setController(this);
                stage.setScene(new Scene(loader.load()));
                stage.setResizable(false);
                stage.setTitle(TITLE);
            } else {
                throw new MalformedURLException();
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void show(){
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        bannerLabel.setVisible(false);
        administratorCheck.setVisible(false);
    }
}
