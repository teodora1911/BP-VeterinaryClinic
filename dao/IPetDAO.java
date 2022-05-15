package dao;

import java.util.List;

import dto.Breed;
import dto.Gender;
import dto.Pet;
import dto.PetOwner;
import dto.Species;

public interface IPetDAO {
    
    List<Species> getSpecies();
    List<Gender> getGenders();
    List<Breed> getBreedsFrom(Species species);
    List<Pet> getPetsFrom(PetOwner owner);
    Pet getDetails(Integer IDPet);
    List<Breed> getBreedsFrom(Pet pet);
    void updatePet(Pet pet);
    void addPetBreed(Pet pet, Breed breed);
    void deletePetBreed(Pet pet, Breed breed);
}
