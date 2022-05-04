package dto;

public class SpentMedicine {
    
    private Medicine medicine;
    private Integer quantity;

    public SpentMedicine(Medicine medicine, Integer quantity){
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public Medicine getMedicine(){
        return medicine;
    }

    public void setMedicine(Medicine medicine){
        this.medicine = medicine;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
}
