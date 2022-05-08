package application.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

import constants.ExaminationChoices;
import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Examination;
import dto.Pet;
import dto.PetOwner;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import mysql.MySQLVeterinarianDAO;

public class ExaminationViewPageController extends InitializableController {

    @FXML private TableView<Examination> table;
    @FXML private TableColumn<Examination, Integer> idColumn;
    @FXML private TableColumn<Examination, Pet> petColumn;
    @FXML private TableColumn<Examination, PetOwner> ownerColumn;
    @FXML private TableColumn<Examination, Date> dateColumn;
    @FXML private TableColumn<Examination, Time> timeColumn;
    @FXML private TableColumn<Examination, String> stateColumn;

    @FXML private ChoiceBox<ExaminationChoices> choiceComboBox;
    @FXML private DatePicker searchDate;
    @FXML private TextField ownerNameField;
    @FXML private TextField ownerSurnameField;

    @FXML private Button refreshButton;
    @FXML private Button updateExaminationButton;
    @FXML private Button startPageButton;

    private ExaminationChoices lastSelectedChoice = null;
    private Date lastSelectedDate = null;
    private String lastNameInput = "*";
    private String lastSurnameInput = "*";
    public ObservableList<Examination> items = FXCollections.observableArrayList();

    public ExaminationViewPageController(Stage stage){
        super(stage, "ExaminationViewPage", "Pregledi");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceComboBox.getItems().setAll(ExaminationChoices.values());
        refreshButton.setOnAction(e -> { refresh(); });
        updateExaminationButton.setOnAction(e -> {
            ExaminationDetailsFormController.currentExamination = table.getSelectionModel().getSelectedItem();
            new ExaminationDetailsFormController().show();
        });
        startPageButton.setOnAction(e -> new VeterinarianStartPageController(stage).show());

        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examination, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Examination, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getIDExamination()).asObject();
            }
		});

        petColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examination, Pet>, ObservableValue<Pet>>(){
			@Override
			public ObservableValue<Pet> call(TableColumn.CellDataFeatures<Examination, Pet> column){
                return new SimpleObjectProperty<Pet>(column.getValue().getPet());
            }
		});

        ownerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examination, PetOwner>, ObservableValue<PetOwner>>(){
			@Override
			public ObservableValue<PetOwner> call(TableColumn.CellDataFeatures<Examination, PetOwner> column){
                return new SimpleObjectProperty<PetOwner>(column.getValue().getPet().getOwner());
            }
		});

        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examination, Date>, ObservableValue<Date>>(){
			@Override
			public ObservableValue<Date> call(TableColumn.CellDataFeatures<Examination, Date> column){
                return new SimpleObjectProperty<Date>(column.getValue().getDate());
            }
		});

        timeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examination, Time>, ObservableValue<Time>>(){
			@Override
			public ObservableValue<Time> call(TableColumn.CellDataFeatures<Examination, Time> column){
                return new SimpleObjectProperty<Time>(column.getValue().getTime());
            }
		});

        stateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examination, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Examination, String> column){
                return new SimpleStringProperty(" " + column.getValue().isCompleted().toString().toUpperCase().charAt(0));
            }
		});
    }

    @FXML
    void search(ActionEvent event) {
        lastSelectedChoice = choiceComboBox.getSelectionModel().getSelectedItem();
        lastSelectedDate = (searchDate.getValue() != null) ? Date.valueOf(searchDate.getValue()) : null;
        lastNameInput = (ownerNameField.getText().isBlank()) ? "*" : "*" + ownerNameField.getText() + "*";
        lastSurnameInput = (ownerNameField.getText().isBlank()) ? "*" : "*" + ownerSurnameField.getText() + "*";
        refresh();
    }
    
    private void refresh() {
        items.clear();
        items.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getExaminationsFrom(MySQLVeterinarianDAO.getCurrentVeterinarian(),
                                                                                                         lastSelectedChoice, lastSelectedDate, lastNameInput, lastSurnameInput));
        table.setItems(items);
    }
}
