package application.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.AppUtil;
import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Appointment;
import dto.Examination;
import dto.Gender;
import dto.Pet;
import dto.Species;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import mysql.MySQLVeterinarianDAO;

public class ScheduleExaminationFormController extends InitializableController {

    @FXML private TextField hoursField;
    @FXML private TextField minutesField;
    @FXML private DatePicker datePicker;
    @FXML private TextField descriptionField;
    @FXML private Label bannerLabel;

    @FXML private TextArea ownerField;
    @FXML TableView<Pet> petTable;
    @FXML private TableColumn<Pet, String> nameColumn;
    @FXML private TableColumn<Pet, Gender> genderColumn;
    @FXML private TableColumn<Pet, Species> speciesColumn;

    public static Appointment appointment;
    
    public ScheduleExaminationFormController(){
        super("ScheduleExaminationForm", "Zakazivanje pregleda");
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        datePicker.setPromptText(appointment.getDate().toString());
        descriptionField.setText(appointment.getEntryReason());
        ownerField.setText(appointment.getPetOwner().toString());

        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pet, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Pet, String> column){
                return new SimpleStringProperty(column.getValue().getName());
            }
		});

        genderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pet, Gender>, ObservableValue<Gender>>(){
			@Override
			public ObservableValue<Gender> call(TableColumn.CellDataFeatures<Pet, Gender> column){
                return new SimpleObjectProperty<Gender>(column.getValue().getGender());
            }
		});

        speciesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pet, Species>, ObservableValue<Species>>(){
			@Override
			public ObservableValue<Species> call(TableColumn.CellDataFeatures<Pet, Species> column){
                return new SimpleObjectProperty<Species>(column.getValue().getSpecies());
            }
		});

        ObservableList<Pet> pets = FXCollections.observableArrayList();
        pets.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getPetsFrom(appointment.getPetOwner()));
        petTable.setItems(pets);
    }

    @FXML
    void submit(ActionEvent event) {
        try{
            if(appointment != null){
                int hours = Integer.parseInt(hoursField.getText());
                int minutes = Integer.parseInt(minutesField.getText());
                bannerLabel.setVisible(false);
                Examination examination = new Examination(null, MySQLVeterinarianDAO.getCurrentVeterinarian(), petTable.getSelectionModel().getSelectedItem(), 
                                                            Date.valueOf(datePicker.getValue()), Time.valueOf(LocalTime.of(hours, minutes)), 
                                                                descriptionField.getText(), null, false, appointment);
                if(!DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().addExamination(examination)){
                    bannerLabel.setVisible(true);
                } else {
                    AppUtil.showAltert(AlertType.CONFIRMATION, "Uspješno ste zakazali pregled.", ButtonType.OK);
                    stage.close();
                }
            } else {
                bannerLabel.setVisible(true);
                System.out.println("Appointment is NULL");
            }
        } catch (Exception e){
            // e.printStackTrace();
            bannerLabel.setVisible(true);
        }
    }
}
