package application.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Examination;
import dto.Payment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ExaminationBillFormController extends InitializableController {

    @FXML private Label totalCostField;
    @FXML private ComboBox<Payment> payment;
    @FXML private Button submitButton;
    @FXML private Button quitButton;

    public static Examination examination;
    private BigDecimal charge;

    public ExaminationBillFormController(Stage stage){
        super(stage, "ExaminationBillForm", "Racun");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(examination != null) {
            charge = DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getTotalCost(examination);
            totalCostField.setText(String.valueOf(charge));
            payment.getItems().addAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().getPayments());

            submitButton.setOnAction(e -> {
                Payment selected = payment.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().createBill(charge, LocalDateTime.now(), selected, examination);
                    stage.close();
                }
            });
            quitButton.setOnAction(e -> stage.close());
        }
    }
    
}
