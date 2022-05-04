package dto;

public class MedicineType {
    
    private Integer IDMedicineType;
    private String type;
    
    public MedicineType(Integer IDMedicineType, String type) {
        this.IDMedicineType = IDMedicineType;
        this.type = type;
    }

    public MedicineType(String type){
        this(null, type);
    }

    public Integer getIDMedicineType() {
        return IDMedicineType;
    }

    public void setIDMedicineType(Integer IDMedicineType) {
        this.IDMedicineType = IDMedicineType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // TODO: Override equals and hashCode methods
    
    @Override
    public String toString(){
        return type;
    }
}
