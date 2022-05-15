package dto;

import java.util.Date;

public class Medicine {
    
    private Integer IDMedicine;
    private String name;
    private Double price;
    private String description;
    private Manufacturer manufacturer;
    private Integer quantity;
    private MedicineType type;
    private Date manufactureDate;
    private Date expirationDate;

    public Medicine(Integer IDMedicine, String name, Double price, String description, Manufacturer manufacturer, Integer quantity, MedicineType type, Date manufactureDate, Date expirationDate) {
        this.IDMedicine = IDMedicine;
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.type = type;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

    public Medicine(Integer IDMedicine, String name, Double price, Manufacturer manufacturer, Integer quantity, MedicineType type){
        this(IDMedicine, name, price, null, manufacturer, quantity, type, null, null);
    }

    public Integer getIDMedicine() {
        return IDMedicine;
    }

    public void setIDMedicine(Integer IDMedicine) {
        this.IDMedicine = IDMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Manufacturer getManufacturer(){
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer){
        this.manufacturer = manufacturer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MedicineType getType(){
        return type;
    }

    public void setType(MedicineType type){
        this.type = type;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        Medicine other = (Medicine)obj;
        if(IDMedicine == null){
            if(other.IDMedicine != null){
                return false;
            }
        } else if(!IDMedicine.equals(other.IDMedicine)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDMedicine == null) ? 0 : IDMedicine.hashCode());
        
        return result;
    }

    @Override
    public String toString(){
        String toReturn = "";
        if(name != null) {
            toReturn += name + "\n";
        }
        if(type != null && manufacturer != null){
            toReturn += "[ " + type.getType() + ", " + manufacturer.getName() + " ]";
        }
        return toReturn;
    }
}
