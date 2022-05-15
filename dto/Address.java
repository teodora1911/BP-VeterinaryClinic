package dto;

public class Address {
    
    private Integer IDAddress;
    private String street;
    private String number;
    private City city;

    public Address(Integer IDAddress, String street, String number, City city){
        this.IDAddress = IDAddress;
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public Address(Integer IDAddress, String street, String number){
        this(IDAddress, street, number, null);
    }

    public Address(String street, String number, City city) {
        this(null, street, number, city);
    }

    public Integer getIDAddress() {
        return IDAddress;
    }

    public void setIDAddress(Integer IDAddress) {
        this.IDAddress = IDAddress;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

        Address other = (Address)obj;
        if(IDAddress == null){
            if(other.IDAddress != null){
                return false;
            }
        } else if(!IDAddress.equals(other.IDAddress)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDAddress == null) ? 0 : IDAddress.hashCode());
        
        return result;
    }

    @Override
    public String toString(){
        return street + " " + number; 
    }
}
