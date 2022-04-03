package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdministratorStartPageController {
    
    @FXML private Button veterinaryViewButton;
    @FXML private Button addNewVetAccountButton;
    @FXML private Button medicineViewButton;
    @FXML private Button addNewMedicineButton;

    @FXML
    protected void veterinaryViewButtonClicked(ActionEvent event){
        System.out.println("Clicked!");
    }

    @FXML
    protected void addNewVetAccountButtonClicked(ActionEvent event){
        System.out.println("Clicked!");
    }

    @FXML
    protected void medicineViewButtonClicked(ActionEvent event){
        System.out.println("Clicked!");
    }

    @FXML
    protected void addNewMedicineButtonClicked(ActionEvent event){
        System.out.println("Clicked!");
    }

    public AdministratorStartPageController(){

    }

}
