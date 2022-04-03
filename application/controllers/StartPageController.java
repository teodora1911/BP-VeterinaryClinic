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
import javafx.stage.Stage;

public class StartPageController {
    
    @FXML private Button loginButton;
    @FXML private Button appointmentButton;
    @FXML private Button pharmacyButton;

    @FXML
    void pharmacyButtonClick(ActionEvent event) {
        System.out.println("Pharmacy button clicked!");
    }

    @FXML
    void appointmentButtonClicked(ActionEvent event) {
        URL url = getClass().getClassLoader().getResource("application" + File.separator + "fxmlfiles" + File.separator + "MakeAppointmentForm.fxml");
        if(url != null){
            FXMLLoader loader = new FXMLLoader(url);
            MakeAppointmentController contoller = new MakeAppointmentController();
            loader.setController(contoller);
            try{
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        System.out.println("Login button clicked!");
    }

    private Stage stage;
    private Scene scene;

    public StartPageController(Stage stage){
        this.stage = stage;
    }
}
