package dto;

public class Species {
    
    private Integer IDSpecies;
    private String name;
    private String description;

    public Species(Integer IDSpecies, String name, String description){
        this.IDSpecies = IDSpecies;
        this.name = name;
        this.description = description;
    }

    public Species(String name){
        this(null, name, null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIDSpecies() {
        return IDSpecies;
    }

    public void setIDSpecies(Integer IDSpecies) {
        this.IDSpecies = IDSpecies;
    }

    // TODO: Override equals and hashCode methods

    @Override
    public String toString(){
        return name;
    }
}
