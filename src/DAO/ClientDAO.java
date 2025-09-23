package DAO;

import model.Client;
import resources.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO {
    private Connection conn;

    public ClientDAO(){
        this.conn= DBConfig.getInstance().getConnection();
    }

    public void AddClient(Client client){

        String sql="INSERT INTO Client(nom,prenom,email,conseiller_id) VALUES (?,?,?,?)";
        try(PreparedStatement prs=conn.prepareStatement(sql))
        {
            prs.setInt(1,client.getId());
            prs.setString(2,client.getNom());
            prs.setString(3,client.getPrenom());
            prs.setString(4,client.getEmail());
            prs.setInt(5,client.getConseiller().getId());
            prs.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    };
    public void DeleteClient(int id){
        String sql="delete from Client where id = ?";
        try(PreparedStatement prs=conn.prepareStatement(sql)){
            prs.setInt(1,id);
            int rows = prs.executeUpdate();
            if(rows > 0) System.out.println("Client supprimé !");
        }catch(SQLException e){
            e.printStackTrace();
        }
    };
    public List<Client> findClientByNomAndPrenom(String nom,String prenom){
        List<Client> clients = new ArrayList<>();

        String sql= "Select * from Client where nom = ? and prenom = ?";
        try(PreparedStatement prs=conn.prepareStatement(sql)){
            prs.setString(1,nom);
            prs.setString(2,prenom);

            try(ResultSet res= prs.executeQuery();){
                while (res.next()){
                    Client client= new Client(
                            res.getInt("id"),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getString("email"),
                            null
                    );
                    clients.add(client);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    };
    public Optional<Client> findClientById(int id){
        String sql= "Select * from Client where id = ? ";
        try(PreparedStatement prs= conn.prepareStatement(sql);){
            prs.setInt(1,id);

            try(ResultSet res=prs.executeQuery();){
                if(res.next()){
                    Client client =  new Client(
                            res.getInt("id"),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getString("email"),
                            null
                    );
                    return Optional.of(client);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    };
    public void ShowListClientParConseiller(int conseiller_id){

        String sql="Select * from Client where conseiller_id = ?";
        try(PreparedStatement prs=conn.prepareStatement(sql)){
            prs.setInt(1,conseiller_id);
            ResultSet rs=prs.executeQuery();
            while (rs.next()) {
                System.out.println("Client: " + rs.getString("nom") + " " + rs.getString("prenom"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    };

}
