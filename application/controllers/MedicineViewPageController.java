package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dao.IMedicineDAO;
import dto.Manufacturer;
import dto.Medicine;
import dto.MedicineType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class MedicineViewPageController extends InitializableController {

    @FXML private ChoiceBox<MedicineType> medicineTypeChoiceBox;
    @FXML private ChoiceBox<Manufacturer> manufacturerChoiceBox;

    @FXML private Button addToExaminationButton;

    @FXML private TableView<Medicine> table;
    @FXML private TableColumn<Medicine, Integer> IDColumn;
    @FXML private TableColumn<Medicine, String> medicineNameColumn;
    @FXML private TableColumn<Medicine, Integer> quantityColumn;
    @FXML private TableColumn<Medicine, Double> priceColumn;
    @FXML private TableColumn<Medicine, String> manufacturerColumn;
    @FXML private TableColumn<Medicine, String> typeColumn;

    public ObservableList<Medicine> items = FXCollections.observableArrayList();
    public boolean examination;

    public MedicineViewPageController(boolean examinationSupport) {
        super("MedicineViewPage", "Pregled lijekova");
        this.examination = examinationSupport;
    }

    @FXML
    void searchMedicine(ActionEvent event) {
        MedicineType selectedMedicineType = medicineTypeChoiceBox.getSelectionModel().getSelectedItem();
        Manufacturer selectedManufacturer = manufacturerChoiceBox.getSelectionModel().getSelectedItem();
        items.clear();
        items.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getMedicineDAO().getMedicineBy(selectedManufacturer, selectedMedicineType));
        table.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IMedicineDAO databaseMedicineController = DAOFactory.getFactory(DAOFactoryType.MySQL).getMedicineDAO();
        medicineTypeChoiceBox.getItems().setAll(databaseMedicineController.getMedicineTypes());
        manufacturerChoiceBox.getItems().setAll(databaseMedicineController.getManufacturers());

        addToExaminationButton.setVisible(examination);
        addToExaminationButton.setOnAction(e -> {
           // TODO:
        });

        IDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Medicine, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getIDMedicine()).asObject();
            }
		});

        medicineNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Medicine, String> column){
                return new SimpleStringProperty(column.getValue().getName());
            }
		});

        quantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Medicine, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getQuantity()).asObject();
            }
		});

        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, Double>, ObservableValue<Double>>(){
			@Override
			public ObservableValue<Double> call(TableColumn.CellDataFeatures<Medicine, Double> column){
                return new SimpleDoubleProperty(column.getValue().getPrice()).asObject();
            }
		});

        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Medicine, String> column){
                return new SimpleStringProperty(column.getValue().getType().getType());
            }
		});

        manufacturerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Medicine, String> column){
                return new SimpleStringProperty(column.getValue().getManufacturer().getName());
            }
		});

        table.setItems(items);
    }
    
}
