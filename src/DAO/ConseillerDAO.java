package DAO;

import model.Client;
import model.Conseiller;
import resources.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean deleteConseiller(int id){
        String sql="Delete from Conseiller where id = ? ";

        try(PreparedStatement prs=conn.prepareStatement(sql)) {

            prs.setInt(1,id);
            int rows=prs.executeUpdate();
            return rows > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public List<Conseiller> showAllConseiller(){
        List<Conseiller>  conseillers= new ArrayList<>();

        String sql="SELECT * FROM Conseiller ";
        try(PreparedStatement prs=conn.prepareStatement(sql);ResultSet rs= prs.executeQuery();
        ){
            while(rs.next()){
                Conseiller conseiller=new Conseiller(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
                conseillers.add(conseiller);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conseillers;
    }
    public Optional<Conseiller> findConseillerById(int id){
        String sql= "SELECT * FROM Conseiller where id= ?";

        try(PreparedStatement prs=conn.prepareStatement(sql);){
            prs.setInt(1,id);
            try(ResultSet res = prs.executeQuery();){
                if(res.next()){
                Conseiller conseiller= new Conseiller(
                        res.getInt("id"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getString("email")
                );
                return Optional.of(conseiller);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Client> findClientConseilleById (int conseiller_id){
        List<Client> clients=new ArrayList<>();

        String sql="SELECT cl.* FROM Conseiller c " +
                "JOIN Client cl ON c.id = cl.conseiller_id "+
                "WHERE cl.conseiller_id = ?";

        try(PreparedStatement prs= conn.prepareStatement(sql)){
            prs.setInt(1,conseiller_id);
            try(ResultSet res = prs.executeQuery()){
                while(res.next()){
                    Client client = new Client(
                            res.getInt("id"),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getString(  "email"),
                            null
                    );
                    clients.add(client);
                }
            }
        }catch ( SQLException e){
            e.printStackTrace();
        }
        return clients;
    }
}
