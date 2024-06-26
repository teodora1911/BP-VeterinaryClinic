package application.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dao.IExaminationDAO;
import dto.Address;
import dto.City;
import dto.Examination;
import dto.ExaminationHasService;
import dto.Pet;
import dto.SpentMedicine;
import dto.Treatment;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML private TextField hoursField;
    @FXML private TextField minutesField;
    @FXML private TextField descriptionTextField;
    
    @FXML private TextField streetTextField;
    @FXML private TextField streetNumberTextField;
    @FXML private ComboBox<City> cityComboBox;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button refreshButton;

    @FXML private TabPane tabPane;

    @FXML private Tab servicesTab;
    @FXML private TableView<ExaminationHasService> serviceTable;
    @FXML private TableColumn<ExaminationHasService, Integer> IDServiceColumn;
    @FXML private TableColumn<ExaminationHasService, String> nameServiceColumn;
    @FXML private TableColumn<ExaminationHasService, Double> priceServiceColumn;
    @FXML private TableColumn<ExaminationHasService, Integer> quantityServiceColumn;

    @FXML private Tab spentMedicinesTab;
    @FXML private TableView<SpentMedicine> spentMedicineTable;
    @FXML private TableColumn<SpentMedicine, Integer> IDMedicineColumn;
    @FXML private TableColumn<SpentMedicine, String> nameMedicineColumn;
    @FXML private TableColumn<SpentMedicine, String> typeMedicineColumn;
    @FXML private TableColumn<SpentMedicine, Integer> spentQuantityColumn;

    @FXML private Tab treatmentsTab;
    @FXML private TableView<Treatment> treatmentsTable;
    @FXML private TableColumn<Treatment, Integer> IDTreatmentMedicineColumn;
    @FXML private TableColumn<Treatment, String> nameTreatmentColumn;
    @FXML private TableColumn<Treatment, Integer> doseTreatmentColumn;
    @FXML private TableColumn<Treatment, String> frequencyTreatmentColumn;
    @FXML private TableColumn<Treatment, Date> startTreatmentColumn;
    @FXML private TableColumn<Treatment, Integer> durationTreatmentColumn;

    public static Examination currentExamination;

    public ObservableList<ExaminationHasService> services;
    public ObservableList<SpentMedicine> spentMedicines;
    public ObservableList<Treatment> treatments;
    
    public ExaminationDetailsFormController(){
        super("ExaminationDetailsForm", "Detalji pregleda");
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        examinationIDLabel.setText(currentExamination.getIDExamination().toString());
        datePicker.setValue(currentExamination.getDate().toLocalDate());
        //datePicker.setPromptText(currentExamination.getDate().toLocalDate().toString());
        hoursField.setText(String.valueOf(currentExamination.getTime().toLocalTime().getHour()));
        minutesField.setText(String.valueOf(currentExamination.getTime().toLocalTime().getMinute()));
        descriptionTextField.setText(currentExamination.getDescription());
        refreshButton.setOnAction(e -> refresh());
        cityComboBox.getItems().addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getCities());
        if(currentExamination.getAddress() != null) {
            streetTextField.setText(currentExamination.getAddress().getStreet());
            streetNumberTextField.setText(currentExamination.getAddress().getNumber());
            cityComboBox.getSelectionModel().select(currentExamination.getAddress().getCity());
        }

        // SERVICE TABLE SETUP
        IDServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExaminationHasService, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExaminationHasService, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getService().getIDService()).asObject();
            }
		});
        nameServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExaminationHasService, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ExaminationHasService, String> column){
                return new SimpleStringProperty(column.getValue().getService().getName());
            }
		});
        priceServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExaminationHasService, Double>, ObservableValue<Double>>(){
			@Override
			public ObservableValue<Double> call(TableColumn.CellDataFeatures<ExaminationHasService, Double> column){
                return new SimpleDoubleProperty(column.getValue().getService().getCost()).asObject();
            }
		});
        quantityServiceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExaminationHasService, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExaminationHasService, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getQuantity()).asObject();
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
        spentQuantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpentMedicine, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SpentMedicine, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getQuantity()).asObject();
            }
		});

        // TREATMENTS TABLE SETUP
        IDTreatmentMedicineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Treatment, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Treatment, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getMedicine().getIDMedicine()).asObject();
            }
		});
        nameTreatmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Treatment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Treatment, String> column){
                return new SimpleStringProperty(column.getValue().getName());
            }
		});
        doseTreatmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Treatment, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Treatment, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getDose()).asObject();
            }
		});
        frequencyTreatmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Treatment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Treatment, String> column){
                return new SimpleStringProperty(column.getValue().getFrequency());
            }
		});
        startTreatmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Treatment, Date>, ObservableValue<Date>>(){
			@Override
			public ObservableValue<Date> call(TableColumn.CellDataFeatures<Treatment, Date> column){
                return new SimpleObjectProperty<Date>(column.getValue().getStartDate());
            }
		});
        durationTreatmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Treatment, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Treatment, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getDuration()).asObject();
            }
		});

        services = FXCollections.observableArrayList();
        spentMedicines = FXCollections.observableArrayList();
        treatments = FXCollections.observableArrayList();
        
        services.clear();
        spentMedicines.clear();
        treatments.clear();
        services.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getServicesFrom(currentExamination));
        serviceTable.setItems(services);
        spentMedicines.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getSpentMedicineFrom(currentExamination));
        spentMedicineTable.setItems(spentMedicines);
        treatments.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getTreatmentsFrom(currentExamination));
        treatmentsTable.setItems(treatments);

        if(currentExamination.isCompleted()){
            datePicker.setEditable(false);
            hoursField.setEditable(false);
            minutesField.setEditable(false);
            descriptionTextField.setEditable(false);
            cityComboBox.setEditable(false);
            streetTextField.setEditable(false);
            streetNumberTextField.setEditable(false);
            addButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            refreshButton.setDisable(true);
        }
    }

    @FXML
    void add(ActionEvent event) {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if(selectedTab != null) {
            if(selectedTab.equals(servicesTab)){
                new ServicesViewController().show();
            } else if (selectedTab.equals(spentMedicinesTab)){
                MedicineViewPageController.add = MedicineViewPageController.quantity = true;
                new MedicineViewPageController().show();
            } else {
                TreatmentDetailsViewController.selectedTreatment = null;
                TreatmentDetailsViewController.selectedMedicine = null;
               new TreatmentDetailsViewController().show();
            }
        }
    }

    @FXML
    void update(ActionEvent event) {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if(selectedTab != null) {
            if(selectedTab.equals(servicesTab)){
                ExaminationHasService selectedS = serviceTable.getSelectionModel().getSelectedItem();
                if(selectedS != null) {
                    UpdateQuantityFormController.selectedS = selectedS;
                    UpdateQuantityFormController.selectedSM = null;
                    new UpdateQuantityFormController().show();
                }
            } else if (selectedTab.equals(spentMedicinesTab)){
                SpentMedicine selectedSM = spentMedicineTable.getSelectionModel().getSelectedItem();
                if(selectedSM != null) {
                    UpdateQuantityFormController.selectedSM = selectedSM;
                    UpdateQuantityFormController.selectedS = null;
                    new UpdateQuantityFormController().show();
                }
            } else {
               Treatment selectedT = treatmentsTable.getSelectionModel().getSelectedItem();
               if(selectedT != null) {
                   TreatmentDetailsViewController.selectedTreatment = selectedT;
                   new TreatmentDetailsViewController().show();
               }
            }
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if(selectedTab != null) {
            if(selectedTab.equals(servicesTab)){
                ExaminationHasService selectedS = serviceTable.getSelectionModel().getSelectedItem();
                if(selectedS != null){
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().deleteService(selectedS);
                }
            } else if (selectedTab.equals(spentMedicinesTab)){
                SpentMedicine selectedSM = spentMedicineTable.getSelectionModel().getSelectedItem();
                if(selectedSM != null) {
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().deleteSpentMedicine(selectedSM);
                }
            } else {
                Treatment selectedT = treatmentsTable.getSelectionModel().getSelectedItem();
                if(selectedT != null) {
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().deleteTreatment(selectedT);
                }
            }
            refresh();
        }
    }

    @FXML
    void viewMedicalRecord(ActionEvent event) {
        Pet pet = DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getDetails(currentExamination.getPet().getIDPet());
        MedicalRecordDetailsFormController.currentPet = pet;
        new MedicalRecordDetailsFormController().show();
    }

    @FXML
    void finalizeExamination(ActionEvent event) {
        if(!currentExamination.isCompleted()){
            City selectedCity = cityComboBox.getSelectionModel().getSelectedItem();

            if(selectedCity != null) {
                Address selectedAddress = new Address(streetTextField.getText(), streetNumberTextField.getText(), selectedCity);
                currentExamination.setAddress(selectedAddress);
                if(datePicker.getValue() != null){
                    currentExamination.setDate(Date.valueOf(datePicker.getValue()));
                }

                try{
                    int hours = Integer.parseInt(hoursField.getText());
                    int minutes = Integer.parseInt(minutesField.getText());
                    if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59){
                        throw new IllegalArgumentException();
                    }
                    
                    String timeStr = String.valueOf(hours) + ":" + String.valueOf(minutes) + ":00";
                    currentExamination.setTime(Time.valueOf(timeStr));

                    currentExamination.setDescription(descriptionTextField.getText());
                    currentExamination.setCompleted(true);
                    if(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().updateExamination(currentExamination)){
                        ExaminationBillFormController.examination = currentExamination;
                        new ExaminationBillFormController(stage).show();
                    }
                } catch (Exception e){
                   System.out.println("Greska!");
                }
            }
        } else {
            stage.close();
        }
    }

    @Override
    public void refresh() {
        IExaminationDAO eDAO = DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if(selectedTab != null) {
            if(selectedTab.equals(servicesTab)){
                services.clear();
                services.addAll(eDAO.getServicesFrom(currentExamination));
                serviceTable.setItems(services);
            } else if(selectedTab.equals(spentMedicinesTab)){
                spentMedicines.clear();
                spentMedicines.addAll(eDAO.getSpentMedicineFrom(currentExamination));
                spentMedicineTable.setItems(spentMedicines);
            } else {
                treatments.clear();
                treatments.addAll(eDAO.getTreatmentsFrom(currentExamination));
                treatmentsTable.setItems(treatments);
            }
        }
    }
}
