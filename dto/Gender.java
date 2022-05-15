package dto;

public class Gender {

    private Integer IDGender;
    private String name;

    public Gender(Integer IDGender, String name){
        this.IDGender = IDGender;
        this.name = name;
    }

    public Gender(String name){
        this(null, name);
    }

    public Integer getIDGender(){
        return IDGender;
    }

    public void setIDGender(Integer IDGender){
        this.IDGender = IDGender;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
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

        Gender other = (Gender)obj;
        if(IDGender == null){
            if(other.IDGender != null){
                return false;
            }
        } else if(!IDGender.equals(other.IDGender)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDGender == null) ? 0 : IDGender.hashCode());
        
        return result;
    }

    @Override
    public String toString(){
        return "" + name.toUpperCase().charAt(0);
    }
}
