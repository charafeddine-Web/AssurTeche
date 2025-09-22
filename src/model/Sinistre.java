package model;
import Enum.TypeSinistre;
import java.time.LocalDate;

public class Sinistre {
    private Integer id;
    private TypeSinistre typeSinistre;
    private LocalDate date;
    private Double cout;
    private String description;
    private Contrat contrat;

    public Sinistre() {}

    public Sinistre(Integer id, TypeSinistre typeSinistre, LocalDate date, Double cout, String description, Contrat contrat) {
        this.id = id;
        this.typeSinistre = typeSinistre;
        this.date = date;
        this.cout = cout;
        this.description = description;
        this.contrat = contrat;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TypeSinistre getTypeSinistre() { return typeSinistre; }
    public void setTypeSinistre(TypeSinistre typeSinistre) { this.typeSinistre = typeSinistre; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }

    @Override
    public String toString() {
        return "Sinistre{" +
                "id=" + id +
                ", typeSinistre=" + typeSinistre +
                ", date=" + date +
                ", cout=" + cout +
                ", description='" + description + '\'' +
                ", contrat=" + (contrat != null ? contrat.getId() : "Aucun") +
                '}';
    }
}

