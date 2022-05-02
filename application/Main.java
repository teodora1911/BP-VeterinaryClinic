package application;

import application.controllers.StartPageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartPageController controller = new StartPageController();
        controller.show();
    }
}
