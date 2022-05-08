package dto;

import java.util.Date;

public class Pet {
    
    private Integer IDPet;
    private String name;
    private Date birthdate;
    private Integer estimatedAge;
    private Gender gender;
    private Double weight;
    private PetOwner owner;
    private Species species;
    private String healthCondition;
    private String diagnosis;
    
    public Pet(Integer IDPet, String name, Gender gender, Double weight, PetOwner owner, Species species, String healthCondition, String diagnosis) {
        this(IDPet, name, null, null, gender, weight, owner, species, healthCondition, diagnosis);
    }

    public Pet(Integer IDPet, String name, Gender gender, PetOwner owner, Species species){
        this(IDPet, name, gender, null, owner, species, null, null);
    }

    public Pet(Integer IDPet, String name, PetOwner owner){
        this.IDPet = IDPet;
        this.name = name;
        this.owner = owner;
    }

    public Pet(Integer IDPet, String name, Date birthdate, Integer estimatedAge, Gender gender, Double weight, PetOwner owner, Species species, String healthCondition, String diagnosis) {
        this.IDPet = IDPet;
        this.name = name;
        this.birthdate = birthdate;
        this.estimatedAge = estimatedAge;
        this.gender = gender;
        this.weight = weight;
        this.owner = owner;
        this.species = species;
        this.healthCondition = healthCondition;
        this.diagnosis = diagnosis;
    }
    
    public Integer getIDPet() {
        return IDPet;
    }

    public void setIDPet(Integer iDPet) {
        this.IDPet = iDPet;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public PetOwner getOwner() {
        return owner;
    }

    public void setOwner(PetOwner owner) {
        this.owner = owner;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getEstimatedAge() {
        return estimatedAge;
    }

    public void setEstimatedAge(Integer estimatedAge) {
        this.estimatedAge = estimatedAge;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
