package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VeterinarianStartPageController extends InitializableController {

    @FXML private Button viewAppointmentsButton;
    @FXML private Button viewExaminationsButton;
    @FXML private Button viewMedicalRecordsButton;

    public VeterinarianStartPageController(Stage stage){
        super(stage, "VeterinarianStartPage", "Dobrodosli");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle bundle){
        // FIXME:
        viewAppointmentsButton.setOnAction(e -> System.out.println(""));
        viewExaminationsButton.setOnAction(e -> System.out.println(""));
        viewMedicalRecordsButton.setOnAction(e -> System.out.println(""));
    }
}
