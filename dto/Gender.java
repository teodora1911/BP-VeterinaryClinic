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

    // TODO: Override equals and hashCode methods

    @Override
    public String toString(){
        return "" + name.toUpperCase().charAt(0);
    }
}
