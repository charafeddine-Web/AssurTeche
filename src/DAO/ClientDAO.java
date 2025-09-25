package DAO;

import model.Client;
import model.Conseiller;
import resources.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import java.util.stream.Collectors;

public class ClientDAO {

    private Connection conn;

    public ClientDAO(){
        this.conn= DBConfig.getInstance().getConnection();
    }

    public void AddClient(Client client){

        String sql="INSERT INTO Client(nom,prenom,email,conseiller_id) VALUES (?,?,?,?)";
        try(PreparedStatement prs=conn.prepareStatement(sql))
        {
//            prs.setInt(1,client.getId());
            prs.setString(1,client.getNom());
            prs.setString(2,client.getPrenom());
            prs.setString(3,client.getEmail());
            prs.setInt(4,client.getConseiller().getId());
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
    public Optional<Client> findClientById(int id){
        String sql= "Select * from Client where id = ? ";
        try(PreparedStatement prs= conn.prepareStatement(sql);){
            prs.setInt(1,id);

            try(ResultSet res=prs.executeQuery();){
                if(res.next()){
                    int conseillerId = res.getInt("conseiller_id");
                    Optional<Conseiller> conseillerOpt = new ConseillerDAO().findConseillerById(conseillerId);
                    Client client =  new Client(
                            res.getInt("id"),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getString("email"),
                            conseillerOpt.orElse(null)
                    );
                    return Optional.of(client);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    };


    public List<Client> ShowListClientParConseiller(int conseillerId) {
        return showAllClient().values().stream()
                .filter(c -> c.getConseiller() != null && c.getConseiller().getId() == conseillerId)
                .collect(Collectors.toList());
    }

    public Map<Integer,Client> showAllClient(){
        Map<Integer,Client> clientMap= new HashMap<>();
        String sql="SELECT * FROM Client ";
        try(PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs= ps.executeQuery();){
            while(rs.next()){
                Client client =  new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        null
                );
                clientMap.put(client.getId(),client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientMap;
    }


//    public Map<Integer, Client> findClientByNomAndPrenom(String nom, String prenom) {
//        Map<Integer, Client> clients = new HashMap<>();
//
//        String sql = "SELECT * FROM Client WHERE nom = ? AND prenom = ?";
//        try (PreparedStatement prs = conn.prepareStatement(sql)) {
//            prs.setString(1, nom);
//            prs.setString(2, prenom);
//
//            try (ResultSet res = prs.executeQuery()) {
//                while (res.next()) {
//                    Client client = new Client(
//                            res.getInt("id"),
//                            res.getString("nom"),
//                            res.getString("prenom"),
//                            res.getString("email"),
//                            null
//                    );
//                    clients.put(client.getId(), client);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return clients;
//    }

//    public List<Client> findClientsByNomPrenom(String nomRecherche, String prenomRecherche) {
//        return showAllClient().values().stream()
//                .filter(c -> c.getNom().equalsIgnoreCase(nomRecherche) &&
//                        c.getPrenom().equalsIgnoreCase(prenomRecherche))
//                .sorted(Comparator.comparing(Client::getNom)) // tri par nom
//                .collect(Collectors.toList());
//    }


}
