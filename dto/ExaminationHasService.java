package dto;

public class ExaminationHasService {
    
    // private Examination examination;
    private Service service;
    private Integer quantity;
    private Double cost;

    public ExaminationHasService(Service service, Integer quantity, Double cost){
        this.setService(service);
        this.setQuantity(quantity);
        this.setCost(cost);
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
