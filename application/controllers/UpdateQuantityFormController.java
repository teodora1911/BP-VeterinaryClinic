package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.ExaminationHasService;
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

    public static SpentMedicine selectedSM;
    public static ExaminationHasService selectedS;

    public UpdateQuantityFormController() {
        super("UpdateQuantityForm", "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(selectedSM != null) {
            mainLabel.setText(selectedSM.toString());
            quantityField.setText(String.valueOf(selectedSM.getQuantity()));
            addButton.setOnAction(e -> {
                try{
                    int newQuantity = Integer.parseInt(quantityField.getText());
                    if(newQuantity < 1) { throw new IllegalArgumentException(); }

                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().updateSpentMedicine(selectedSM, newQuantity);
                    stage.close();
                } catch (Exception exc){
                    bannerLabel.setVisible(true);
                }
            });
        } else if (selectedS != null) {
            mainLabel.setText(selectedS.toString());
            quantityField.setText(String.valueOf(selectedS.getQuantity()));
            addButton.setOnAction(e -> {
                try{
                    int newQuantity = Integer.parseInt(quantityField.getText());
                    if(newQuantity < 1) { throw new IllegalArgumentException(); }

                    DAOFactory.getFactory(DAOFactoryType.MySQL).getExaminationDAO().updateService(selectedS, newQuantity);
                    stage.close();
                } catch (Exception exc){
                    bannerLabel.setVisible(true);
                }
            });
        }
    }
    
}
