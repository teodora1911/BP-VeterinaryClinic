package dto;

public class PetOwner {
    
    private Integer IDPetOwner;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    public PetOwner(Integer IDPetOwner, String name, String surname, String email, String phoneNumber){
        this.IDPetOwner = IDPetOwner;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public PetOwner(String name, String surname, String email, String phoneNumber){
        this(null, name, surname, email, phoneNumber);
    }

    public Integer getIDPetOwner() {
        return IDPetOwner;
    }

    public void setIDPetOwner(Integer IDPetOwner) {
        this.IDPetOwner = IDPetOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

        PetOwner other = (PetOwner)obj;
        if(IDPetOwner == null){
            if(other.IDPetOwner != null){
                return false;
            }
        } else if(!IDPetOwner.equals(other.IDPetOwner)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDPetOwner == null) ? 0 : IDPetOwner.hashCode());

        return result;
    }

    @Override
    public String toString(){
        String toReturn = surname + ", " + name;
        toReturn += (email != null) ? (", " + email) : "";
        toReturn += (phoneNumber != null) ? (", " + phoneNumber) : "";
        return toReturn;
    }
}
