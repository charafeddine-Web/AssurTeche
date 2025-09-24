package service;

import DAO.ClientDAO;
import model.Client;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService(){
        this.clientDAO= new ClientDAO();
    }

    public void addClient(Client client){
        clientDAO.AddClient(client);
    }

    public void deleteClient(int id){
        clientDAO.DeleteClient(id);
    }

    public Optional<Client> findClientById(int id){
        return clientDAO.findClientById(id);
    };

    public Map<Integer,Client> findClientByNomAndPrenom(String nom,String prenom){
        Map<Integer,Client> clients= clientDAO.findClientByNomAndPrenom(nom,prenom);
        clients.values().stream()
                .map(c-> c.getNom() + " " + c.getPrenom())
                .forEach(System.out::println);

        return clients;
    }

    public void showListClientByConseiller(int conseiller_id){
        List<Client> clients= clientDAO.ShowListClientParConseiller(conseiller_id);

        clients.stream()
                .map(c-> c.getNom() +" "+ c.getPrenom())
                .forEach(System.out::println);
    }

}
