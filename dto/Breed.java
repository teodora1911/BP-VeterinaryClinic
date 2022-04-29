package dto;

public class Breed {
    
    private Integer IDBreed;
    private String name;
    private String description;
    private Species species;

    public Breed(Integer IDBreed, String name, String description, Species species) {
        this.IDBreed = IDBreed;
        this.name = name;
        this.description = description;
        this.species = species;
    }

    public Species getSpecies() {
        return species;
    }

    public Integer getIDBreed() {
        return IDBreed;
    }

    public void setIDBreed(Integer IDBreed) {
        this.IDBreed = IDBreed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    // TODO: Override equals, hashCode and toString methods
}
