package dao;

import java.util.HashMap;

public interface IVeterinarianDAO {
    // preko indeksa mozda 
    public HashMap<String, String> getVeterinariansFullName();
    public Integer getVeterinaianID(String name, String surname);
    public boolean authenticateVeterinarian(String username, String password);
}
