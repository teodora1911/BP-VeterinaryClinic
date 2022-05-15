package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Service;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ServicesViewController extends InitializableController {

    @FXML private TableView<Service> table;
    @FXML private TableColumn<Service, Integer> idColumn;
    @FXML private TableColumn<Service, String> nameColumn;
    @FXML private TableColumn<Service, Double> priceColumn;
    @FXML private TextField serviceNumber;
    @FXML private Button addButton;

    public ServicesViewController(){
        super("ServicesView", "Usluge");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Integer>, ObservableValue<Integer>>(){
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Service, Integer> column){
                return new SimpleIntegerProperty(column.getValue().getIDService()).asObject();
            }
		});
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Service, String> column){
                return new SimpleStringProperty(column.getValue().getName());
            }
		});
        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Double>, ObservableValue<Double>>(){
			@Override
			public ObservableValue<Double> call(TableColumn.CellDataFeatures<Service, Double> column){
                return new SimpleDoubleProperty(column.getValue().getCost()).asObject();
            }
		});

        ObservableList<Service> items = FXCollections.observableArrayList();
        items.addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getVeterinarianDAO().getServices());
        table.setItems(items);

        addButton.setOnAction(e -> {
            try{
                int serviceQuantity = Integer.parseInt(serviceNumber.getText());
                Service selectedService = table.getSelectionModel().getSelectedItem();
                if(selectedService != null && (serviceQuantity > 0)) {
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().addService(ExaminationDetailsFormController.currentExamination, selectedService, serviceQuantity);
                }
            } catch(Exception exc){
                System.out.println("Nije unesen validan broj.");
            }
        });
    }
    
}
