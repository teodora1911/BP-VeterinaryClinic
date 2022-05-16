package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import application.AppUtil;
import dao.IPetDAO;
import dto.Breed;
import dto.Gender;
import dto.Pet;
import dto.PetOwner;
import dto.Species;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MySQLPetDAO implements IPetDAO {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;

    private void resetAll(){
        connection = null;
        ps = null;
        cs = null;
        rs = null;
    }

    /**
     * pet_details_view
     * 
     * 1.  IDPet
     * 2.  Name
     * 3.  Birthdate
     * 4.  EstimatedAge
     * 5.  GenderName
     * 6.  Weight
     * 7.  SpeciesName
     * 8.  HealthCondition
     * 9.  Diagnosis
     * 10. IDOwner
     * 11. IDGender
     * 12. IDSpecies
     */

    @Override
    public List<Pet> getPetsFrom(PetOwner owner) {
        resetAll();
        if(owner == null){
            return null;
        }

        List<Pet> pets = new ArrayList<>();
        final String query = "SELECT IDPet, Name, Gender, Species, IDOwner FROM pet_details_view pv WHERE pv.IDOwner=?";

        try{
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, owner.getIDPetOwner());
            rs = ps.executeQuery();

            while(rs.next()){
                Pet pet = new Pet(rs.getInt(1), rs.getString(2), 
                                    new Gender(rs.getString(3)), owner, new Species(rs.getString(4)));
                pets.add(pet);
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return pets;
    }

    @Override
    public Pet getDetails(Integer IDPet){
        resetAll();
        String query = "SELECT * FROM pet_details_view WHERE IDPet=?";
        Pet pet = null;
        
        try{
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, IDPet);
            rs = ps.executeQuery();

            if(rs.first()){
                Species species = (rs.getObject(12) != null) ? (new Species(rs.getInt(12), rs.getString(7), null)) : null;
                Gender gender = (rs.getObject(11) != null) ? (new Gender(rs.getInt(11), rs.getString(5))) : null;

                pet = new Pet(rs.getInt(1), rs.getString(2), rs.getDate(3), (Integer)rs.getObject(4), gender, (Double)rs.getObject(6), null, species, rs.getString(8), rs.getString(9));
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return pet;
    }

    @Override
    public List<Breed> getBreedsFrom(Pet pet) {
        resetAll();
        final String query = "SELECT phb.Pet, phb.Breed, b.Name, b.Description FROM pethasbreed phb LEFT JOIN breed b ON phb.Breed=b.IDBreed WHERE phb.Pet=?";
        List<Breed> breeds = new ArrayList<>();
        if(pet != null && pet.getIDPet() != null) {
            try{
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, pet.getIDPet());
                rs = ps.executeQuery();
                /**
                 * 1. IDPet
                 * 2. IDBreed
                 * 3. BreedName
                 * 4. Description
                 */
                while(rs.next()){
                    Breed breed = new Breed(rs.getInt(2), rs.getString(3), rs.getString(4), null);
                    breeds.add(breed);
                }
            } catch(SQLException e){
                AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }

        return breeds;
    }

    @Override
    public List<Species> getSpecies() {
        resetAll();
        List<Species> species = new ArrayList<>();
        final String query = "SELECT * FROM species";
        try{
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                Species s = new Species(rs.getInt(1), rs.getString(2), rs.getString(3));
                species.add(s);
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, rs);
        }
        
        return species;
    }

    @Override
    public List<Gender> getGenders() {
        resetAll();
        List<Gender> genders = new ArrayList<>();
        final String query = "SELECT * FROM gender";
        try{
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                Gender gender = new Gender(rs.getInt(1), rs.getString(2));
                genders.add(gender);
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, rs);
        }
        
        return genders;
    }

    @Override
    public List<Breed> getBreedsFrom(Species species) {
        resetAll();
        List<Breed> breeds = new ArrayList<>();
        if (species != null && species.getIDSpecies() != null) {
            final String query = "SELECT * FROM breed WHERE Species=?";
            try{
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, species.getIDSpecies());
                rs = ps.executeQuery();

                while(rs.next()) {
                    Breed breed = new Breed(rs.getInt(1), rs.getString(2), rs.getString(3), species);
                    breeds.add(breed);
                }
            } catch (SQLException e){
                AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }

        return breeds;
    }
    
    @Override
    public void updatePet(Pet pet) {
        if(pet != null && pet.getIDPet() != null) {
            resetAll();
            final String query = "{CALL update_pet(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try {
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);

                cs.setInt(1, pet.getIDPet());
                cs.setString(2, pet.getName());
                if(pet.getBirthdate() != null) { cs.setDate(3, pet.getBirthdate()); } else { cs.setNull(3, Types.DATE); }
                cs.setInt(4, (pet.getEstimatedAge() != null) ? pet.getEstimatedAge() : 0);
                cs.setInt(5, pet.getGender().getIDGender());
                cs.setDouble(6, (pet.getWeight() != null) ? pet.getWeight() : 0);
                cs.setInt(7, pet.getSpecies().getIDSpecies());
                cs.setString(8, pet.getHealthCondition());
                cs.setString(9, pet.getDiagnosis());
                cs.registerOutParameter(10, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(10)){
                    AppUtil.showAltert(AlertType.ERROR, "Neupjesno azuriranje", ButtonType.OK);
                }
            } catch (SQLException e) {
                AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void addPetBreed(Pet pet, Breed breed) {
        if(pet != null && breed != null) {
            resetAll();
            final String query = "{CALL add_pet_breed(?, ?)}";
            try{
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, pet.getIDPet());
                cs.setInt(2, breed.getIDBreed());
                cs.execute();
            } catch (SQLException e){
                AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void deletePetBreed(Pet pet, Breed breed) {
        if(pet != null && breed != null) {
            resetAll();
            final String query = "DELETE FROM pethasbreed WHERE Pet=? AND Breed=?";
            try{
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, pet.getIDPet());
                ps.setInt(2, breed.getIDBreed());
                ps.execute();
            } catch (SQLException e){
                AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }
    }
}
