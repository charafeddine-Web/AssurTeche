package service;

import DAO.ConseillerDAO;
import model.Client;
import model.Conseiller;

import java.util.Optional;
import java.util.*;

public class ConseillerService {

    private final ConseillerDAO conseillerDAO;

    public ConseillerService(){
        this.conseillerDAO = new ConseillerDAO();
    }

    public void addConseiller(Conseiller conseiller){
        conseillerDAO.addConseiller(conseiller);
    }

    public void deleteConseiller(int id){
        conseillerDAO.deleteConseiller(id);
    }

    public Map<Integer,Conseiller> showAllConseiller(){
        Map<Integer,Conseiller> conseillers = conseillerDAO.showAllConseiller();
        conseillers.values().stream()
                .map(co ->co.getId() + "- " + co.getNom() + " "+ co.getPrenom()+" "+co.getEmail())
                .forEach(System.out::println);
        return  conseillers;
    }

    public Optional<Conseiller> findConseillerById(int id){
        Optional<Conseiller> conseiller=  conseillerDAO.findConseillerById(id);
        conseiller.ifPresent(c ->
                System.out.println(("Conseiller: " + c.getNom() + " " + c.getPrenom() +
                        " | Email: " + c.getEmail() + " | ID: " + c.getId()))
        );
                return conseiller;
    };

    public List<Client> findClientConseilleById(int conseiller_id) {
        List<Client> clients=conseillerDAO.findClientConseilleById(conseiller_id);
        clients.stream()
                .map(c-> "- " + c.getNom() + " " + c.getPrenom() + " " + c.getEmail())
                .forEach(System.out::println);

        return clients;
    }


}
