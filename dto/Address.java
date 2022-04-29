package dto;

public class Address {
    
    private Integer IDAddress;
    private String Street;
    private String Number;
    private City City;

    public Address(Integer IDAddress, String street, String number, City city){
        this.IDAddress = IDAddress;
        this.Street = street;
        this.Number = number;
        this.City = city;
    }

    public int getIDAddress() {
        return IDAddress;
    }

    public void setIDAddress(int iDAddress) {
        this.IDAddress = iDAddress;
    }

    public City getCity() {
        return this.City;
    }

    public void setCity(City city) {
        this.City = city;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        this.Street = street;
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
        return "[" + IDAddress + ", " + Street + ", " + Number + ", " + this.City.getName() + "]"; 
    }
}
