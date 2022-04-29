package dto;

public class City {
    
    private Integer IDCity;
    private String Name;
    private String ZIPCode;

    public City() { }

    public City(Integer IDCity, String Name, String ZIPCode){
        this.IDCity = IDCity;
        this.Name = Name;
        this.ZIPCode = ZIPCode;
    }

    public String getZIPCode() {
        return ZIPCode;
    }

    public void setZIPCode(String zIPCode) {
        this.ZIPCode = zIPCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getIDCity() {
        return IDCity;
    }

    public void setIDCity(int iDCity) {
        this.IDCity = iDCity;
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

        City other = (City)obj;
        if(IDCity == null){
            if(other.IDCity != null){
                return false;
            }
        } else if(!IDCity.equals(other.IDCity)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDCity == null) ? 0 : IDCity.hashCode());
        
        return result;
    }

    @Override
    public String toString(){
        return "[" + IDCity + ", " + Name + ", " + ZIPCode + "]";
    }
}
