package dto;

public class Species {
    
    private Integer IDSpecies;
    private String name;
    private String description;

    public Species(Integer IDSpecies, String name, String description){
        this.IDSpecies = IDSpecies;
        this.name = name;
        this.description = description;
    }

    public Species(String name){
        this(null, name, null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIDSpecies() {
        return IDSpecies;
    }

    public void setIDSpecies(Integer IDSpecies) {
        this.IDSpecies = IDSpecies;
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

        Species other = (Species)obj;
        if(IDSpecies == null){
            if(other.IDSpecies != null){
                return false;
            }
        } else if(!IDSpecies.equals(other.IDSpecies)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDSpecies == null) ? 0 : IDSpecies.hashCode());
        
        return result;
    }

    @Override
    public String toString(){
        return name;
    }
}
