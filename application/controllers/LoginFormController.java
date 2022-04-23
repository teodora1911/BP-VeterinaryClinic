package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController {

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

    public LoginFormController(Stage stage){
        this.stage = stage;
    }
}
