package dao;

import java.util.List;

import dto.Pet;
import dto.PetOwner;

public interface IPetDAO {
    
    List<Pet> getPetsFrom(PetOwner owner);
}
