package dto;

public class Veterinarian {
    
    private Integer IDVeterinarian;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String homeNumber;
    private Address homeAddress;
    private String username;
    private String password;

    public Veterinarian(Integer IDVeterinarian, String name, String surname, String email, String phoneNumber, String homeNumber, Address homeAddress, String username, String password){
        this.IDVeterinarian = IDVeterinarian;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeNumber = homeNumber;
        this.homeAddress = homeAddress;
        this.username = username;
        this.password = password;
    }

    public Integer getIDVeterinarian() {
        return IDVeterinarian;
    }

    public void setIDVeterinarian(Integer IDVeterinarian) {
        this.IDVeterinarian = IDVeterinarian;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
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

        Veterinarian other = (Veterinarian)obj;
        if(IDVeterinarian == null){
            if(other.IDVeterinarian != null){
                return false;
            }
        } else if(!IDVeterinarian.equals(other.IDVeterinarian)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 97;
        int result = 1;
        result = prime * result + ((IDVeterinarian == null) ? 0 : IDVeterinarian.hashCode());

        return result;
    }

    @Override
    public String toString(){
        return "[ " + IDVeterinarian + ", " + name + ", " + surname + " ]";
    }
}
