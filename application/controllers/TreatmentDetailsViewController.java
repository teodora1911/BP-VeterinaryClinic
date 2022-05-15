package application.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import application.AppUtil;
import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Medicine;
import dto.Treatment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TreatmentDetailsViewController extends InitializableController {

    @FXML private TextField nameField;
    @FXML private DatePicker startDate;
    @FXML private TextField doseField;
    @FXML private TextField durationField;
    @FXML private TextField frequencyField;
    @FXML private TextField instructionsField;
    @FXML private Label medicineLabel;
    @FXML private Button submitButton;
    @FXML private Button medicineSearch;
    @FXML private Button refreshButton;

    public static Medicine selectedMedicine;
    public static Treatment selectedTreatment;

    public TreatmentDetailsViewController() {
        super("TreatmentDetailsView", "Recept");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medicineSearch.setOnAction(e -> {
            MedicineViewPageController.add = true;
            MedicineViewPageController.quantity = false;
            new MedicineViewPageController().show();
        });
        if(selectedTreatment != null) {
            nameField.setText(selectedTreatment.getName());
            startDate.setPromptText(selectedTreatment.getStartDate().toString());
            doseField.setText(String.valueOf(selectedTreatment.getDose()));
            durationField.setText(String.valueOf(selectedTreatment.getDuration()));
            frequencyField.setText(selectedTreatment.getFrequency());
            instructionsField.setText(selectedTreatment.getInstructions());
            medicineLabel.setText(selectedTreatment.getMedicine().toString());
            selectedMedicine = selectedTreatment.getMedicine();
            medicineSearch.setDisable(true);
        }      
        refreshButton.setOnAction(e -> refresh());  
        submitButton.setOnAction(e -> {
            try {
                int dose = Integer.parseInt(doseField.getText());
                int duration = Integer.parseInt(durationField.getText());
                if(dose < 1 || duration < 1) {
                    throw new IllegalArgumentException("...");
                }

                if(selectedTreatment != null) {
                    selectedTreatment.setName(nameField.getText());
                    selectedTreatment.setDose(dose);
                    selectedTreatment.setFrequency(frequencyField.getText());
                    if(startDate.getValue() != null) {
                        selectedTreatment.setStartDate(Date.valueOf(startDate.getValue()));
                    }
                    selectedTreatment.setDuration(duration);
                    selectedTreatment.setInstructions(instructionsField.getText());
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().updateTreatment(selectedTreatment);
                } else {
                    if(startDate.getValue() == null) {
                        throw new IllegalArgumentException();
                    }
                    Treatment treatment = new Treatment(ExaminationDetailsFormController.currentExamination, selectedMedicine,
                                                         nameField.getText(), dose, frequencyField.getText(), Date.valueOf(startDate.getValue()), duration, instructionsField.getText());
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().addTreatment(treatment);
                }

                stage.close();
            } catch (Exception exc) {
                AppUtil.showAltert(AlertType.ERROR, "Niste unijeli korektne parametre.", ButtonType.FINISH);
            }
        });
    }
    
    @Override
    public void refresh(){
        if(selectedMedicine != null) {
            medicineLabel.setText(selectedMedicine.toString());
        }
    }
}
