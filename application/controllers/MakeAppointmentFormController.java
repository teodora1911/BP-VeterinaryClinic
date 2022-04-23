package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MakeAppointmentFormController {
    
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField emailField;
    @FXML ComboBox<String> vetComboBox;
    @FXML private ComboBox<String> speciesComboBox;
    @FXML DatePicker datePicker;
    @FXML TextArea descriptionField;

    @FXML
    protected void submitButtonClicked(ActionEvent event){
        // TODO: Implementirati metodu na odgovarajuci nacin
        System.out.println("Submitted!");
    }

    public MakeAppointmentFormController(){

    }
}
