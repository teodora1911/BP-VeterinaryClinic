package dao;

import java.util.List;

import dto.Service;
import dto.Veterinarian;

public interface IVeterinarianDAO {
    List<Veterinarian> getVeterinarians();
    boolean authenticateVeterinarian(String username, String password);
    List<Service> getServices();  // List<Service> getServicesFrom(Veterinarian veterinarian);
}
