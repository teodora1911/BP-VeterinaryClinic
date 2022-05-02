package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class AppUtil {
    
    private AppUtil() { }

    private static final int WIDTH = 500;
    private static final int HEIGHT = 100;

    public static void showAltert(AlertType alertType, String message, ButtonType buttonType){
        Alert alert = new Alert(alertType, message, buttonType);
        alert.setWidth(WIDTH);
        alert.setHeight(HEIGHT);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.show();
    }
}
