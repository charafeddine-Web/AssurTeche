package View;
import model.Contrat;
import model.Sinistre;
import service.ContratService;
import service.SinistreService;
import Enum.TypeSinistre;
import java.time.LocalDate;
import java.util.*;

public class SinistreView {

    private Scanner scanner;
    private SinistreService sinistreService;

    public SinistreView(){
        this.scanner=new Scanner(System.in);
        this.sinistreService = new SinistreService();
    }


    public void menu(){
        int choix;

        do {
            System.out.println("\n===== Gestion des Sinistres =====");
            System.out.println("1 - Ajouter un sinistre");
            System.out.println("2 - Supprimer un sinistre");
            System.out.println("3 - Rechercher un sinistre par ID");
            System.out.println("4 - Afficher sinistres par contrat");
            System.out.println("5 - Afficher sinistres par client");
            System.out.println("6 - Afficher sinistres triés par montant (desc)");
            System.out.println("7 - Afficher sinistres avant une date donnée");
            System.out.println("8 - Afficher sinistres supérieurs à un montant");
            System.out.println("9 - Calculer le coût total des sinistres d'un client");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option : ");

            try {
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        addSinistre();
                        break;
                    case 2:
                        deleteSinistre();
                        break;
                    case 3:
                        findSinistreById();
                        break;
                    case 4:
                        showSinistresByContrat();
                        break;
                    case 5:
                        showSinistresByClient();
                        break;
                    case 6:
                        showSinistresByMontantDesc();
                        break;
                    case 7:
                        showSinistresAvantDate();
                        break;
                    case 8:
                        showSinistresSuperieurMontant();
                        break;
                    case 9:
                        calculerCoutTotalClient();
                        break;
                    case 0:
                        System.out.println("Au revoir");
                        break;
                    default:
                        System.out.println("Option invalide !");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Erreur: veuillez entrer un nombre valide !");
                scanner.nextLine();
                choix = -1;
            }
        }while (choix !=0);
    }


    private void addSinistre() {

        System.out.println("Types de Sinistre disponibles :");
        Arrays.stream(TypeSinistre.values())
                .forEach(t -> System.out.println("- " + t.name()));

        System.out.print("Choisissez le type de sinistre : ");
        String typeStr = scanner.nextLine().trim().toUpperCase();
        TypeSinistre typeSinistre;
        try {
             typeSinistre = TypeSinistre.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Type de sinistre invalide !");
            return;
        }

        System.out.print("Entrez la date du sinistre (yyyy-MM-dd) : ");
        String dateStr = scanner.nextLine();
        LocalDate date;

        try {
            date = LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Date invalide !");
            return;
        }

        System.out.print("Entrez le coût du sinistre : ");
        double cout = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez une description : ");
        String description = scanner.nextLine();

        ContratService contratService = new ContratService();
        List<Contrat> contrats = contratService.showAllContrat();

        if (contrats.isEmpty()) {
            System.out.println(" Aucun contrat trouvé  impossible d’ajouter un sinistre !");
            return;
        }
        System.out.println("=== Liste des Contrats disponibles");
        contrats.forEach(c -> System.out.println(
                "ID: " + c.getId() +
                        " | Type: " + c.getTypeContrat() +
                        " | Début: " + c.getDateDebut() +
                        " | Fin: " + c.getDateFin()
        ));

        System.out.print("Choisissez l’ID du contrat : ");
        int contratId = scanner.nextInt();
        scanner.nextLine();

        Contrat contrat = contrats.stream()
                .filter(c -> c.getId() == contratId)
                .findFirst()
                .orElse(null);

        if (contrat == null) {
            System.out.println(" Cotrat introuvable !");
            return;
        }

        Sinistre sinistre = new Sinistre(null, typeSinistre, date, cout, description, contrat);

        sinistreService.addSinistre(sinistre);

        System.out.println(" Sinistre ajouté avec succès !");
    }

    private void deleteSinistre() {
        System.out.print("Entrez l'ID du sinistre à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (sinistreService.deleteSinistre(id)) {
            System.out.println("Sinistre supprimé !");
        } else {
            System.out.println("Sinistre introuvable !");
        }
    }

    private void findSinistreById() {
        System.out.print("Entrez l'ID du sinistre : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Sinistre> sinistreOpt = sinistreService.findSinistreById(id);
        if (sinistreOpt.isPresent()) {
            Sinistre s = sinistreOpt.get();
            System.out.println("=== Détails du Sinistre ===");
            System.out.println("ID : " + s.getId());
            System.out.println("Type : " + s.getTypeSinistre());
            System.out.println("Date : " + s.getDate());
            System.out.println("Coût : " + s.getCout());
            System.out.println("Description : " + s.getDescription());
            System.out.println("Contrat ID : " + (s.getContrat() != null ? s.getContrat().getId() : "Aucun"));
        } else {
            System.out.println("Sinistre introuvable !");
        }
    }

    private void showSinistresByContrat() {
        System.out.print("Entrez l'ID du contrat : ");
        int contratId = scanner.nextInt();
        scanner.nextLine();
        List<Sinistre> sinistres = sinistreService.showSinistresByContratId(contratId);
        sinistres.forEach(System.out::println);
    }

    private void showSinistresByClient() {
        System.out.print("Entrez l'ID du client : ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        List<Sinistre> sinistres = sinistreService.showSinistreByClientId(clientId);
        sinistres.forEach(System.out::println);
    }

    private void showSinistresByMontantDesc() {
        List<Sinistre> sinistres = sinistreService.showSinistreTreeByMontant();
        sinistres.forEach(System.out::println);
    }

    private void showSinistresAvantDate() {
        System.out.print("Entrez la date limite (yyyy-MM-dd) : ");
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Date invalide !");
            return;
        }
        List<Sinistre> sinistres = sinistreService.showSinistreAvantDateDonner(date);
        sinistres.forEach(System.out::println);
    }

    private void showSinistresSuperieurMontant() {
        System.out.print("Entrez le montant minimum : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        List<Sinistre> sinistres = sinistreService.afficherSinistreParCoutSuperieurMontant(montant);
        sinistres.forEach(System.out::println);
    }

    private void calculerCoutTotalClient() {
        System.out.println("Entrez l’ID du client : ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        SinistreService sinistreService = new SinistreService();
        double total = sinistreService.calculCoutsTotauxByClientId(clientId);

        System.out.println("Coût total des sinistres du client " + clientId + " = " + total);
    }



}
