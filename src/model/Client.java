package model;

import java.util.ArrayList;
import java.util.List;

public class Client  extends  Person{
    private Integer id;
    private List<Contrat> contrats = new ArrayList<>();
    private Conseiller conseiller;

    public Client (Integer id,String nom, String prenom,String email, Conseiller conseiller){
        super(nom,prenom,email);
        this.id=id;
        this.conseiller=conseiller;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conseiller getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
    }
    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

}
