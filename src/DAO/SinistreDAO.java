package DAO;

import model.Contrat;
import model.Sinistre;
import resources.DBConfig;
import Enum.TypeSinistre;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import  java.util.*;
import java.util.stream.Collectors;

import model.Sinistre;

public class SinistreDAO {

    private Connection conn;

    public SinistreDAO(){
        this.conn= DBConfig.getInstance().getConnection();
    }

    public void addSinistre(Sinistre sinistre) {
        String sql = "INSERT INTO Sinistre(typeSinistre, date, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sinistre.getTypeSinistre() != null ? sinistre.getTypeSinistre().name() : null);

            if (sinistre.getDate() != null) {
                ps.setDate(2, Date.valueOf(sinistre.getDate()));
            } else {
                ps.setDate(2, null);
            }

            ps.setDouble(3, sinistre.getCout());
            ps.setString(4, sinistre.getDescription());
            ps.setInt(5, sinistre.getContrat() != null ? sinistre.getContrat().getId() : 0);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteSinistre(int id){
        String sql= "DELETE FROM Sinistre WHERE id = ?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    };

    public List<Sinistre> showAllSinistres() {
        List<Sinistre> sinistres = new ArrayList<>();
        String sql = "SELECT * FROM Sinistre";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sinistre sinistre = new Sinistre();
                sinistre.setId(rs.getInt("id"));
                sinistre.setTypeSinistre(TypeSinistre.valueOf(rs.getString("typeSinistre")));
                sinistre.setDate(rs.getDate("date").toLocalDate());
                sinistre.setCout(rs.getDouble("cout"));
                sinistre.setDescription(rs.getString("description"));

                Contrat contrat = new Contrat();
                contrat.setId(rs.getInt("contrat_id"));
                sinistre.setContrat(contrat);

                sinistres.add(sinistre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinistres;
    }



    public Optional<Sinistre> findSinistreById(int id) {
        return showAllSinistres().stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public List<Sinistre> showSinistresByContratId(int contratId) {
        return showAllSinistres().stream()
                .filter(s -> s.getContrat() != null && s.getContrat().getId() == contratId)
                .collect(Collectors.toList());
    }

    public List<Sinistre> showSinistreTreeByMontant() {
        return showAllSinistres().stream()
                .sorted(Comparator.comparingDouble(Sinistre::getCout).reversed())
                .collect(Collectors.toList());
    }
    public List<Sinistre> showSinistreByClientId(int clientId) {
        return showAllSinistres().stream()
                .filter(s -> s.getContrat() != null
                        && s.getContrat().getClient() != null
                        && s.getContrat().getClient().getId() == clientId)
                .collect(Collectors.toList());
    }
    public List<Sinistre> showSinistreAvantDateDonner(LocalDate date) {
        return showAllSinistres().stream()
                .filter(s -> s.getDate().isBefore(date))
                .collect(Collectors.toList());
    }

    public List<Sinistre> afficherSinistreParCoutSuperieurMontant(double montant) {
        return showAllSinistres().stream()
                .filter(s -> s.getCout() > montant)
                .collect(Collectors.toList());
    }

}
