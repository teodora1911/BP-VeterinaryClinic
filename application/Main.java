package application;

import java.io.File;
import java.net.URL;

import application.controllers.StartPageController;
// import application.controllers.LoginPageController;
// import application.controllers.UserStartPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static String DIR = System.getProperty("user.dir") + File.separator + "application" + File.separator + "resources" + File.separator;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println("application/fxmlfiles/StartPageForm.fxml");
        URL url = getClass().getClassLoader().getResource("application" + File.separator + "resources" + File.separator + "StartPage.fxml");
        if(url == null){
            System.out.println("Problem sa URL-om");
            System.exit(-1);
        }
        FXMLLoader loader = new FXMLLoader(url);
        StartPageController controller = new StartPageController(primaryStage);
        loader.setController(controller);

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Pawspiracy");
        primaryStage.show();
    }
}
