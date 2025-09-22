package model;
import Enum.TypeContrat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contrat {
    private Integer id;
    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Client client;
    private List<Sinistre> sinistres = new ArrayList<>();

    public Contrat() {}

    public Contrat(Integer id, TypeContrat typeContrat, LocalDate dateDebut, LocalDate dateFin, Client client) {
        this.id = id;
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TypeContrat getTypeContrat() { return typeContrat; }
    public void setTypeContrat(TypeContrat typeContrat) { this.typeContrat = typeContrat; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Sinistre> getSinistres() { return sinistres; }
    public void setSinistres(List<Sinistre> sinistres) { this.sinistres = sinistres; }


    @Override
    public String toString(){
        return "Contrat {"+
                ", TypeContrat :"+getTypeContrat()+
                ", Date Debut :"+getDateDebut()+
                ", Date Fin :"+getDateFin()+
                ", Client :"+(client != null ? client.getNom()+" "+client.getPrenom() : "Aucun")+
                "}";

    }
}
