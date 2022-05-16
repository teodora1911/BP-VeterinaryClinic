package dto;

public class ExaminationHasService {
    
    private Examination examination;
    private Service service;
    private Integer quantity;
    private Double cost;

    public ExaminationHasService(Examination examination, Service service, Integer quantity, Double cost){
        this.examination = examination;
        this.service = service;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Examination getExamination(){
        return examination;
    }

    public void setExamination(Examination examination){
        this.examination = examination;
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

    @Override
    public String toString(){
        return (service != null) ? service.toString() : "N/A";
    }
}
