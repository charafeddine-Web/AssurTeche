package model;

import java.util.ArrayList;
import java.util.List;

public class Client  extends  Person{

    private List<Contrat> contrats = new ArrayList<>();
    private Conseiller conseiller;

    public Client (String nom, String prenom,String email, Conseiller conseiller){
        super(nom,prenom,email);
        this.conseiller=conseiller;
    }

    public Conseiller getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
    }
}
