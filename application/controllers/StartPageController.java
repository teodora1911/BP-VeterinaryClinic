package application.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPageController {

    @FXML
    void makeAppointment(ActionEvent event) {
        URL url = getClass().getClassLoader().getResource("application" + File.separator + "fxmlfiles" + File.separator + "MakeAppointmentForm.fxml");
        if(url != null){
            FXMLLoader loader = new FXMLLoader(url);
            MakeAppointmentFormController controller = new MakeAppointmentFormController();
            loader.setController(controller);
            try{
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                controller.setStage(newStage);
                newStage.show();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void login(ActionEvent event){
        URL url = getClass().getClassLoader().getResource("application" + File.separator + "fxmlfiles" + File.separator + "LoginForm.fxml");
        if(url != null){
            FXMLLoader loader = new FXMLLoader(url);
            LoginFormController contoller = new LoginFormController(stage);
            loader.setController(contoller);
            try{
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.setTitle("Pawspiracy");
                stage.show();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private Stage stage;

    public StartPageController(Stage stage){
        this.stage = stage;
    }
}
