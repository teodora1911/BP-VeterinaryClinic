package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController extends InitializableController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button submitButton;
    @FXML private Label bannerLabel;

    @FXML
    protected void submitButtonAction(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(!username.isBlank() && !password.isBlank() &&
            DAOFactory.getFactory(DAOFactoryType.MySQL).getVeterinarianDAO().authenticateVeterinarian(username, password)){
            VeterinarianStartPageController controller = new VeterinarianStartPageController(stage);
            controller.show();
        } else {
            bannerLabel.setVisible(true);
        }
    }

    public LoginFormController(Stage stage){
        super(stage, "LoginForm", "Prijava");
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        bannerLabel.setVisible(false);
    }
}
