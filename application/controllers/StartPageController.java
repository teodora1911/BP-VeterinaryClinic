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
import javafx.stage.Stage;

public class StartPageController implements Initializable {

    @FXML
    void makeAppointment(ActionEvent event) {
        MakeAppointmentFormController controller = new MakeAppointmentFormController();
        controller.show();
    }

    @FXML
    void login(ActionEvent event){
        LoginFormController controller = new LoginFormController(stage);
        controller.show();
    }

    private Stage stage;
    private static final String RESOURCE = "application" + File.separator + "resources" + File.separator + "StartPage.fxml";
    private static final String TITLE = "Dobrodosli";

    public StartPageController(){
        this.stage = new Stage();
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
        // nothing to do
    }
}
