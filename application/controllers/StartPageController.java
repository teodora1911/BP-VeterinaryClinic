package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartPageController extends InitializableController {

    @FXML private Button makeAppointmentButton;
    @FXML private Button loginButton;

    public StartPageController(){
        super("StartPage", "Dobrodosli");
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        makeAppointmentButton.setOnAction(e -> new MakeAppointmentFormController().show());
        loginButton.setOnAction(e -> new LoginFormController(stage).show());
    }
}
