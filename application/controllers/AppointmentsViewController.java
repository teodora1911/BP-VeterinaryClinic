package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import constants.AppointmentsChoices;
import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Appointment;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import mysql.MySQLVeterinarianDAO;

public class AppointmentsViewController extends InitializableController {
    
    @FXML private ComboBox<AppointmentsChoices> choicesComboBox;

    @FXML private TableView<Appointment> table;
    @FXML private TableColumn<Appointment, Integer> IDColumn;
    @FXML private TableColumn<Appointment, String> nameColumn;
    @FXML private TableColumn<Appointment, String> surnameColumn;
    @FXML private TableColumn<Appointment, String> emailColumn;
    @FXML private TableColumn<Appointment, String> dateColumn;
    @FXML private TableColumn<Appointment, String> entryReasonColumn;

    @FXML private Button startPageButton;
    @FXML private Button scheduleExaminationButton;

    public ObservableList<Appointment> items;
    private AppointmentsChoices lastSelectedChoice = null;


    @FXML
    void choiceSelected(ActionEvent event) {
        AppointmentsChoices selectedChoice = choicesComboBox.getSelectionModel().getSelectedItem();
        if(selectedChoice != null){
            lastSelectedChoice = selectedChoice;
            refreshView(event);
            // items.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).
            //             getAppointmentDAO().getAppointmentsFromVeterinarian(IDVeterinarian, lastSelectedChoice));
            // table.setItems(items);
        }
    }

    @FXML
    void refreshView(ActionEvent event) {
        if(lastSelectedChoice != null && MySQLVeterinarianDAO.getCurrentVeterinarian() != null) {
            items.clear();
            items.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getAppointmentDAO().
                        getAppointmentsFrom(MySQLVeterinarianDAO.getCurrentVeterinarian(), lastSelectedChoice));   
            table.setItems(items);
        }
    }

    public AppointmentsViewController(Stage stage){
        super(stage, "AppointmentsView", "Pregled zathjeva");
        items = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        choicesComboBox.getItems().setAll(AppointmentsChoices.values());
        startPageButton.setOnAction(e -> new VeterinarianStartPageController(stage).show());
        scheduleExaminationButton.setOnAction(e -> {
            Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
            if(selectedAppointment != null){
                ScheduleExaminationFormController.appointment = selectedAppointment;
                new ScheduleExaminationFormController().show();
            }
        });

        IDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Appointment, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getIDAppointment()).asObject();
            }
		});
        
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> column){
                return new SimpleStringProperty(column.getValue().getPetOwner().getName());
            }
		});

        surnameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> column){
                return new SimpleStringProperty(column.getValue().getPetOwner().getSurname());
            }
		});

        emailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> column){
                return new SimpleStringProperty(column.getValue().getPetOwner().getEmail());
            }
		});

        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> column){
                return new SimpleStringProperty(column.getValue().getDate().toString());
            }
		});

        entryReasonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> column){
                return new SimpleStringProperty(column.getValue().getEntryReason());
            }
		});

        table.setItems(items);
    }
}