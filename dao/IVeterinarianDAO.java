package dao;

import java.util.List;

import dto.Veterinarian;

public interface IVeterinarianDAO {
    public List<Veterinarian> getVeterinarians();
    // public Integer getVeterinaianID(String name, String surname);
    public boolean authenticateVeterinarian(String username, String password);
}
