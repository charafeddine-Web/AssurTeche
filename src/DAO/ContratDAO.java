package DAO;

import com.mysql.cj.xdevapi.Type;

import model.Client;
import model.Contrat;
import resources.DBConfig;
import Enum.TypeContrat;
import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContratDAO {

    private  Connection conn;

    public ContratDAO(){
        this.conn = DBConfig.getInstance().getConnection();
    }

    public void addContrat(Contrat contrat){
        String sql = "INSERT INTO Contrat (typeContrat, dateDebut, dateFin, client_id) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setString(1, contrat.getTypeContrat() != null ? contrat.getTypeContrat().name() : null);

            if (contrat.getDateDebut() != null) {
                ps.setDate(2, Date.valueOf(contrat.getDateDebut()));
            } else {
                ps.setNull(2, Types.DATE);
            }

            if(contrat.getDateFin() != null){
                ps.setDate(3,Date.valueOf(contrat.getDateFin()));
            }else {
                ps.setNull(3, Types.DATE);
            }

            if(contrat.getClient() != null && contrat.getClient().getId() !=null){
                ps.setInt(4, contrat.getClient().getId());
            }else {
                ps.setNull(4,Types.INTEGER);
            }
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    contrat.setId(keys.getInt(1));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    };
    public Optional<Contrat> showContratById(int id ){

        String sql="SELECT * FROM Contrat WHERE id = ?";

        try(PreparedStatement ps=conn.prepareStatement(sql))
        {
            ps.setInt(1,id);

            try(ResultSet res= ps.executeQuery()){
                if(res.next()){
                    Contrat contrat = new Contrat(
                            res.getInt("id"),
                            TypeContrat.valueOf(res.getString("typeContrat")),
                            res.getDate("dateDebut").toLocalDate(),
                            res.getDate("dateFin").toLocalDate(),
                            null
                    );
                    return Optional.of(contrat);
                };
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public boolean deleteContrat(int id){
        String sql= "DELETE FROM Contrat WHERE id = ?";

        try(PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setInt(1,id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }catch ( SQLException e){
            System.err.println("Erreur lors de la suppression du contrat avec ID: " + id);
            e.printStackTrace();
        }
        return false;
    };
    public List<Contrat> findContratsByClientId(int clientId) {
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM Contrat WHERE client_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contrat contrat = new Contrat(
                            rs.getInt("id"),
                            TypeContrat.valueOf(rs.getString("typeContrat")),
                            rs.getDate("dateDebut").toLocalDate(),
                            rs.getDate("dateFin").toLocalDate(),
                            null
                    );
                    contrats.add(contrat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        contrats.stream()
//                .forEach(c -> System.out.println(
//                        "ID: " + c.getId() +
//                                " | Type: " + c.getTypeContrat() +
//                                " | Début: " + c.getDateDebut() +
//                                " | Fin: " + c.getDateFin()
//                ));

        return contrats;
    }



}
