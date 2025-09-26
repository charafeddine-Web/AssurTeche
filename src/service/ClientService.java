package service;

import DAO.ClientDAO;
import model.Client;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Client> showListClientByConseiller(int conseiller_id){
        return clientDAO.ShowListClientParConseiller(conseiller_id);
    }
    public List<Client> findClientsByNomPrenom(String nomRecherche) {
    return showAllClient().values().stream()
            .filter(c -> c.getNom().equalsIgnoreCase(nomRecherche))
            .sorted(Comparator.comparing(Client::getNom))
            .collect(Collectors.toList());
}
    public Map<Integer, Client> showAllClient() {
        return clientDAO.showAllClient();
    }


}
