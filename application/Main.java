package application;

import java.io.File;

import application.controllers.StartPageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static String DIR = System.getProperty("user.dir") + File.separator + "application" + File.separator + "resources" + File.separator;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartPageController controller = new StartPageController();
        System.out.println("Pokretanje aplikacije - START");
        controller.show();
    }
}
