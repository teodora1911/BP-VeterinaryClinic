package dto;

public class Payment {
    
    private Integer IDPayment;
    private String type;

    public Payment(Integer IDPayment, String type) {
        this.IDPayment = IDPayment;
        this.type = type;
    }

    public Integer getIDPayment(){
        return IDPayment;
    }

    public void setIDPayment(Integer IDPayment) {
        this.IDPayment = IDPayment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
