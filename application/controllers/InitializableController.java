package application.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class InitializableController implements Initializable {
    
    private static final String RESOURCE_DIR = "application" + File.separator + "resources" + File.separator;
    private static final String RESOURCE_EXTENSION = ".fxml";

    protected Stage stage;
    protected String resource;
    protected String title;

    public InitializableController(Stage primaryStage, String filename, String title){
        this.stage = (primaryStage == null) ? new Stage() : primaryStage;
        this.resource = RESOURCE_DIR + filename + RESOURCE_EXTENSION;
        this.title = title;

        try{
            URL url = getClass().getClassLoader().getResource(this.resource);
            if(url != null){
                FXMLLoader loader = new FXMLLoader(url);
                loader.setController(this);
                stage.setScene(new Scene(loader.load()));
                stage.setResizable(false);
                stage.setTitle(this.title);
            } else {
                throw new MalformedURLException();
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public InitializableController(String filename, String title){
        this(null, filename, title);
    }

    public void show(){
        stage.show();
    }

    public void refresh() { }
}
