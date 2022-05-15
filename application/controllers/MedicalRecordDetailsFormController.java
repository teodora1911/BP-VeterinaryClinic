package application.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactoryType;
import dto.Breed;
import dto.Gender;
import dto.Pet;
import dto.Species;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MedicalRecordDetailsFormController extends InitializableController {


    @FXML private TextField nameField;

    @FXML private TextField dayField;
    @FXML private TextField monthField;
    @FXML private TextField yearField;
    @FXML private TextField ageField;
    @FXML private TextField weightField;

    @FXML private ComboBox<Gender> genderComboBox;
    @FXML private ComboBox<Species> speciesComboBox;
    @FXML private TextField healthCondition;
    @FXML private TextField diagnosis;

    @FXML private ComboBox<Breed> breedComboBox;
    @FXML private TableView<Breed> table;
    @FXML private TableColumn<Breed, String> breedColumn;

    public static Pet currentPet = null;

    private Species lastSelectedSpecies = null;

    @FXML
    void addBreed(ActionEvent event) {
        Breed selectedBreed = breedComboBox.getSelectionModel().getSelectedItem();
        if(selectedBreed != null) {
            DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().addPetBreed(currentPet, selectedBreed);
            refresh();
        }
    }

    @FXML
    void deleteBreed(ActionEvent event) {
        Breed selected = table.getSelectionModel().getSelectedItem();
        if(selected != null) {
            DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().deletePetBreed(currentPet, selected);
            refresh();
        }
    }

    @FXML
    void submit(ActionEvent event) {
        if(currentPet != null) {
            Species selectedSpecies = speciesComboBox.getSelectionModel().getSelectedItem();
            Gender selectGender = genderComboBox.getSelectionModel().getSelectedItem();
            if(selectGender != null && selectedSpecies != null) {
                currentPet.setName(nameField.getText());
                try{
                    int day = Integer.parseInt(dayField.getText());
                    int month = Integer.parseInt(monthField.getText());
                    int year = Integer.parseInt(yearField.getText());

                    if(day > 0 && month > 0 && year > 0) {
                        String strDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
                        currentPet.setBirthdate(Date.valueOf(strDate));
                    } else { throw new IllegalArgumentException(); }
                } catch (Exception e){
                    currentPet.setBirthdate(null);
                }

                try{
                    int estimatedAge = Integer.parseInt(ageField.getText());
                    if(estimatedAge < 1) { throw new IllegalArgumentException(); }
                    currentPet.setEstimatedAge(estimatedAge);
                } catch (Exception e) {
                    currentPet.setEstimatedAge(0);
                }

                try{
                    double weight = Double.parseDouble(weightField.getText());
                    if(weight < 1) { throw new IllegalArgumentException(); }
                    currentPet.setWeight(weight);
                } catch (Exception e) {
                    currentPet.setWeight(0.0);
                }

                currentPet.setSpecies(selectedSpecies);
                currentPet.setGender(selectGender);
                currentPet.setHealthCondition(healthCondition.getText());
                currentPet.setDiagnosis(diagnosis.getText());

                DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().updatePet(currentPet);
                stage.close();
            }
        }
    }

    public MedicalRecordDetailsFormController(){
        super("MedicalRecordDetailsForm", "Karton");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(currentPet != null) {
            nameField.setText(currentPet.getName());
            if(currentPet.getBirthdate() != null){
                LocalDate date = currentPet.getBirthdate().toLocalDate();
                dayField.setText(String.valueOf(date.getDayOfMonth()));
                monthField.setText(String.valueOf(date.getMonthValue()));
                yearField.setText(String.valueOf(date.getYear()));
            }
            if(currentPet.getEstimatedAge() != null) {
                ageField.setText(String.valueOf(currentPet.getEstimatedAge()));
            }
            if(currentPet.getWeight() != null) {
                weightField.setText(String.valueOf(currentPet.getWeight()));
            }

            genderComboBox.getItems().setAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getGenders());
            if(currentPet.getGender() != null) {
                genderComboBox.getSelectionModel().select(currentPet.getGender());
            }
            speciesComboBox.getItems().setAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getSpecies());
            if(currentPet.getSpecies() != null) {
                speciesComboBox.getSelectionModel().select(currentPet.getSpecies());
                lastSelectedSpecies = currentPet.getSpecies();
                breedComboBox.getItems().setAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getBreedsFrom(lastSelectedSpecies));
            }
            speciesComboBox.setOnAction(e -> {
                lastSelectedSpecies = speciesComboBox.getSelectionModel().getSelectedItem();
                breedComboBox.getItems().setAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getBreedsFrom(lastSelectedSpecies));
            });

            healthCondition.setText((currentPet.getHealthCondition() != null) ? currentPet.getHealthCondition() : "");
            diagnosis.setText((currentPet.getDiagnosis() != null) ? currentPet.getDiagnosis() : "");

            breedColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Breed, String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Breed, String> column){
                    return new SimpleStringProperty(" " + column.getValue().getName());
                }
            });
            refresh();
        }
    }

    @Override
    public void refresh(){
        ObservableList<Breed> breeds = FXCollections.observableArrayList();
        breeds.setAll(DAOFactory.getFactory(DAOFactoryType.MySQL).getPetDAO().getBreedsFrom(currentPet));
        table.setItems(breeds);
    }
}
