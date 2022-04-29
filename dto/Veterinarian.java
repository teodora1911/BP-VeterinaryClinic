package dto;

public class Veterinarian {
    
    private Integer IDVeterinarian;
    private String Name;
    private String Surname;
    private String Email;
    private String PhoneNumber;
    private String HomeNumber;
    private Address HomeAddress;
    private String Username;
    private String Password;

    public Veterinarian(int IDVeterinarian, String name, String surname, String email, String phoneNumber, String homeNumber, Address homeAddress, String username, String password){
        this.IDVeterinarian = IDVeterinarian;
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.HomeNumber = homeNumber;
        this.HomeAddress = homeAddress;
        this.Username = username;
        this.Password = password;
    }

    public Integer getIDVeterinarian() {
        return IDVeterinarian;
    }

    public void setIDVeterinarian(Integer iDVeterinarian) {
        this.IDVeterinarian = iDVeterinarian;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public Address getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.HomeAddress = homeAddress;
    }

    public String getHomeNumber() {
        return HomeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.HomeNumber = homeNumber;
    }
    
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
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
        return "[" + IDVeterinarian + ", " + Name + ", " + Surname + "]";
    }
}
