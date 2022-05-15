package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.SpentMedicine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateQuantityFormController extends InitializableController {
    
    @FXML private Label mainLabel;
    @FXML private TextField quantityField;
    @FXML private Button addButton;
    @FXML private Label bannerLabel;

    public static SpentMedicine selected;

    public UpdateQuantityFormController() {
        super("UpdateQuantityForm", "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(selected != null) {
            mainLabel.setText(selected.toString());
            quantityField.setText(String.valueOf(selected.getQuantity()));
            addButton.setOnAction(e -> {
                try{
                    int newQuantity = Integer.parseInt(quantityField.getText());
                    if(newQuantity < 1) { throw new IllegalArgumentException(); }

                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().updateSpentMedicine(selected, newQuantity);
                    stage.close();
                } catch (Exception exc){
                    bannerLabel.setVisible(true);
                }
            });
        }
    }
    
}
