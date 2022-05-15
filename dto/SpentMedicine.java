package dto;

public class SpentMedicine {
    
    private Examination examination;
    private Medicine medicine;
    private int quantity;

    public SpentMedicine(Examination examination, Medicine medicine, int quantity){
        this.examination = examination;
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public Examination getExamination(){
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

    public Medicine getMedicine(){
        return medicine;
    }

    public void setMedicine(Medicine medicine){
        this.medicine = medicine;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String toString(){
       return (medicine != null) ? medicine.toString() : "";
    }
}
