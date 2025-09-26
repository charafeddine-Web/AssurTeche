package service;

import DAO.ContratDAO;
import model.Contrat;

import java.util.List;
import java.util.Optional;

public class ContratService {

    private ContratDAO contratDAO;

    public  ContratService(){
        this.contratDAO= new ContratDAO();
    }
    public void addContrat(Contrat contrat){
        contratDAO.addContrat(contrat);
    }
    public Optional<Contrat> showContratById(int id){
        return  contratDAO.showContratById(id);
    }
    public boolean deleteContrat(int id ){
       return contratDAO.deleteContrat(id);
    };
    public List<Contrat> findContratsByClientId(int clientid){
        return contratDAO.findContratsByClientId(clientid);
    }

    public List<Contrat> showAllContrat(){return contratDAO.showAllContrat();};
}
