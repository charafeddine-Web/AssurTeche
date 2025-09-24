package DAO;

import model.Sinistre;
import resources.DBConfig;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.util.*;
import java.sql.Connection;
import model.Sinistre;

public class SinistreDAO {

    private Connection conn;

    public SinistreDAO(){
        this.conn= DBConfig.getInstance().getConnection();
    }

    public void addSinistre(Sinistre sinistre) {
        String sql = "INSERT INTO Sinistre(typeSinistre, date, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sinistre.getTypeSinistre() != null ? sinistre.getTypeSinistre().name() : null);

            if (sinistre.getDate() != null) {
                ps.setDate(2, Date.valueOf(sinistre.getDate()));
            } else {
                ps.setDate(2, null);
            }

            ps.setDouble(3, sinistre.getCout());
            ps.setString(4, sinistre.getDescription());
            ps.setInt(5, sinistre.getContrat() != null ? sinistre.getContrat().getId() : 0);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteSinistre(int id){
        String sql= "DELETE FROM Sinistre WHERE id = ?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    };

//    public void calculCoutsTotaux(){};
//    public Map<Integer,Sinistre> findSinistreById(){};
//    public Map<Integer,Sinistre> showSinistresByContratId(){};
//    public List<Sinistre> showSinistreTreeByMontant(){};
//    public List<Sinistre> showSinistreByClientId(){};


}
