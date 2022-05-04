package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dto.Address;
import dto.City;
import dto.Examination;
import dto.Service;
import dto.SpentMedicine;
import dto.Treatment;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ExaminationDetailsFormController extends InitializableController {

    @FXML private Label examinationIDLabel;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<Integer> hoursComboBox;
    @FXML private ComboBox<Integer> minutesComboBox;
    @FXML private TextField descriptionTextField;
    
    @FXML private TextField addressTextBox;
    @FXML private ComboBox<Address> addressComboBox;
    @FXML private ComboBox<City> cityComboBox;

    @FXML private TabPane tabPane;

    @FXML private Tab servicesTab;
    @FXML private TableView<Service> serviceTable;
    @FXML private TableColumn<Service, Integer> IDServiceColumn;
    @FXML private TableColumn<Service, String> nameServiceColumn;
    @FXML private TableColumn<Service, Double> priceServiceColumn;

    @FXML private Tab spentMedicinesTab;
    @FXML private TableView<SpentMedicine> spentMedicineTable;
    @FXML private TableColumn<SpentMedicine, Integer> IDMedicineColumn;
    @FXML private TableColumn<SpentMedicine, String> nameMedicineColumn;
    @FXML private TableColumn<SpentMedicine, String> typeMedicineColumn;
    @FXML private TableColumn<SpentMedicine, String> manufacturerColumn;
    @FXML private TableColumn<SpentMedicine, Integer> spentQuantityColumn;

    @FXML private Tab treatmentsTab;
    @FXML private TableView<Treatment> treatmentsTable;
    @FXML private TableColumn<Treatment, Integer> IDTreatmentMedicineColumn;
    @FXML private TableColumn<Treatment, String> nameTreatmentColumn;
    @FXML private TableColumn<Treatment, Integer> doseTreatmentColumn;
    @FXML private TableColumn<Treatment, String> frequencyTreatmentColumn;
    @FXML private TableColumn<Treatment, String> startTreatmentColumn;
    @FXML private TableColumn<Treatment, Integer> durationTreatmentColumn;
    @FXML private TableColumn<Treatment, String> instructionsTreatmentColumn;

    public Examination currentExamination;
    
    public ExaminationDetailsFormController(Examination examination){
        super("ExaminationDetailsForm", "Detalji pregleda");
        currentExamination = examination;
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        examinationIDLabel.setText(currentExamination.getIDExamination().toString());
        datePicker = new DatePicker(currentExamination.getDate().toLocalDate());
        hoursComboBox.getItems().setAll(IntStream.range(0, 24).boxed().collect(Collectors.toList()));
        minutesComboBox.getItems().setAll(IntStream.range(0, 60).filter(e -> e % 5 == 0).boxed().collect(Collectors.toList()));
        hoursComboBox.setPromptText(String.valueOf(currentExamination.getTime().toLocalTime().getHour()));
        minutesComboBox.setPromptText(String.valueOf(currentExamination.getTime().toLocalTime().getMinute()));
        descriptionTextField.setText(currentExamination.getDescription());

        // SERVICE TABLE SETUP
        IDServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Service, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getIDService()).asObject();
            }
		});
        nameServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Service, String> column){
                return new SimpleStringProperty(column.getValue().getName());
            }
		});
        priceServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Double>, ObservableValue<Double>>(){
			@Override
			public ObservableValue<Double> call(TableColumn.CellDataFeatures<Service, Double> column){
                return new SimpleDoubleProperty(column.getValue().getIDService()).asObject();
            }
		});

        // SPENT MEDICINE TABLE SETUP
        IDMedicineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpentMedicine, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SpentMedicine, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getMedicine().getIDMedicine()).asObject();
            }
		});
        nameMedicineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpentMedicine, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<SpentMedicine, String> column){
                return new SimpleStringProperty(column.getValue().getMedicine().getName());
            }
		});
        typeMedicineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpentMedicine, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<SpentMedicine, String> column){
                return new SimpleStringProperty(column.getValue().getMedicine().getType().getType());
            }
		});
        manufacturerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpentMedicine, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<SpentMedicine, String> column){
                return new SimpleStringProperty(column.getValue().getMedicine().getManufacturer().getName());
            }
		});
        spentQuantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpentMedicine, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SpentMedicine, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getQuantity()).asObject();
            }
		});

        // TREATMENTS TABLE SETUP

    }

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void viewMedicalRecord(ActionEvent event) {

    }

    @FXML
    void finalizeExamination(ActionEvent event) {

    }
}
