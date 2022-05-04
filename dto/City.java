package dto;

public class City {
    
    private Integer IDCity;
    private String name;
    private String ZIPCode;

    public City() { }

    public City(Integer IDCity, String name, String ZIPCode){
        this.IDCity = IDCity;
        this.name = name;
        this.ZIPCode = ZIPCode;
    }

    public String getZIPCode() {
        return ZIPCode;
    }

    public void setZIPCode(String ZIPCode) {
        this.ZIPCode = ZIPCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIDCity() {
        return IDCity;
    }

    public void setIDCity(int IDCity) {
        this.IDCity = IDCity;
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
        return name;
    }
}
