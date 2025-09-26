package service;

import DAO.SinistreDAO;
import model.Sinistre;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SinistreService {

    SinistreDAO sinistreDAO;

    public SinistreService(){
        this.sinistreDAO = new SinistreDAO();
    }

    public void addSinistre(Sinistre sinistre){
        sinistreDAO.addSinistre(sinistre);
    };

   public boolean deleteSinistre(int id){
       return sinistreDAO.deleteSinistre(id);
   };

    public double calculCoutsTotauxByClientId(int client_id) {
        List<Sinistre> sinistres = sinistreDAO.showAllSinistres();

        return sinistreDAO.showAllSinistres().stream()
                .filter(s -> s.getContrat() != null
                        && s.getContrat().getClient() != null
                        && s.getContrat().getClient().getId() == client_id)
                .distinct() // يشيل duplications (خاص Sinistre يعرف equals/hashCode)
                .mapToDouble(Sinistre::getCout)
                .sum();
    }



    public Optional<Sinistre> findSinistreById(int id) {
        return sinistreDAO.findSinistreById(id);
    }

    public List<Sinistre> showSinistresByContratId(int contratId) {
        return sinistreDAO.showSinistresByContratId(contratId);
    }

    public List<Sinistre> showSinistreTreeByMontant() {
        return sinistreDAO.showSinistreTreeByMontant();
    }

    public List<Sinistre> showSinistreByClientId(int clientId) {
        return sinistreDAO.showSinistreByClientId(clientId);
    }

    public List<Sinistre> showSinistreAvantDateDonner(LocalDate date) {
        return sinistreDAO.showSinistreAvantDateDonner(date);
    }

    public List<Sinistre> afficherSinistreParCoutSuperieurMontant(double montant) {
        return sinistreDAO.afficherSinistreParCoutSuperieurMontant(montant);
    }

}
