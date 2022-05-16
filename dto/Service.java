package dto;

public class Service {
    
    private Integer IDService;
    private String name;
    private Double cost;
    private String description;

    public Service(Integer IDService, String name, Double cost, String description){
        this.setIDService(IDService);
        this.setName(name);
        this.setCost(cost);
        this.setDescription(description);
    }

    public Service(Integer IDService, String name, Double cost){
        this(IDService, name, cost, null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIDService() {
        return IDService;
    }

    public void setIDService(Integer iDService) {
        this.IDService = iDService;
    }

    @Override
    public String toString(){
        return name;
    }
}
