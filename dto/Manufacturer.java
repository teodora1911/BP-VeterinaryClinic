package dto;

public class Manufacturer {
    
    private Integer IDManufacturer;
    private String name;
    private String description;
    
    public Manufacturer(Integer IDManufacturer, String name, String description) {
        this.IDManufacturer = IDManufacturer;
        this.name = name;
        this.description = description;
    }

    public Integer getIDManufacturer() {
        return IDManufacturer;
    }

    public void setIDManufacturer(Integer IDManufacturer) {
        this.IDManufacturer = IDManufacturer;
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

    // TODO: Override equals, hashCode and toString methods
}
