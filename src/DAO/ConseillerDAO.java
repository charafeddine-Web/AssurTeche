package DAO;

import model.Client;
import model.Conseiller;
import resources.DBConfig;

import java.sql.*;

public class ConseillerDAO {

    private Connection conn;

    public ConseillerDAO(){
        this.conn= DBConfig.getInstance().getConnection();
    }

    public void addConseiller(Conseiller conseiller){

        String sql="INSERT INTO Conseiller(nom,prenom,email) VALUES(?,?,?)";
        try(PreparedStatement prs= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            prs.setString(1, conseiller.getNom());
            prs.setString(2,conseiller.getPrenom());
            prs.setString(3,conseiller.getEmail());
            prs.executeUpdate();
            try(ResultSet res=prs.getGeneratedKeys()){
                if(res.next()){
                    conseiller.setId(res.getInt(1));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
