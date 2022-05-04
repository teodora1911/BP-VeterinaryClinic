package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ScheduleExaminationFormController extends InitializableController {

    @FXML private ComboBox<Integer> hours;
    @FXML private ComboBox<Integer> minutes;
    @FXML private DatePicker datePicker;
    @FXML private TextField descriptionField;
    
    public ScheduleExaminationFormController(){
        super("ScheduleExaminationForm", "Zakazivanje pregleda");
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        hours.getItems().setAll(IntStream.range(0, 24).boxed().collect(Collectors.toList()));
        minutes.getItems().setAll(IntStream.range(0, 60).filter(e -> e % 5 == 0).boxed().collect(Collectors.toList()));
    }

    @FXML
    void submit(ActionEvent event) {

    }
}
