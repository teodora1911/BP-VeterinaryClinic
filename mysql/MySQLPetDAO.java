package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPetDAO;
import dto.Gender;
import dto.Pet;
import dto.PetOwner;
import dto.Species;

public class MySQLPetDAO implements IPetDAO {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void resetAll(){
        connection = null;
        ps = null;
        rs = null;
    }

    @Override
    public List<Pet> getPetsFrom(PetOwner owner) {
        resetAll();
        if(owner == null){
            return null;
        }

        List<Pet> pets = new ArrayList<>();
        /*
         * pet_details_view
         * IDPet, Name, Birthdate, EstimatedAge, Gender, Weight, Species, HealthCondiiton, Diagnosis, IDOwner
         *
         */
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
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return pets;
    }
    
}
