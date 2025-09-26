package View;

import model.Client;
import model.Contrat;
import service.ClientService;
import service.ContratService;
import Enum.TypeContrat;

import java.time.LocalDate;
import java.util.*;

public class ContratView {

    private Scanner scanner ;
    private ContratService contratService;

    public ContratView(){
        this.contratService=new ContratService();
        this.scanner = new Scanner(System.in);
    }

    public void menu(){
        int choix;

        do {
            System.out.println("\n===== Gestion des Contrat =====");
            System.out.println("1 - Ajouter un Contrat");
            System.out.println("2 - Afficher un Contrat par ID");
            System.out.println("3 - Rechercher un Contrat par ID Client ");
            System.out.println("4 - Supprimer un Contrat");
            System.out.println("5 - Return Au Menu Prancipal ");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option : ");

            choix =scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    addContrat();
                    break;
                case 2:
                    findContratById();
                    break;
                case 3:
                    findContratByIdClient();
                    break;
                case 4:
                    deleteContrat();
                    break;
                case 5:
                    return;
                case 0 : System.out.println("Au revoir");break;
                default : System.out.println("Option invalide !");break;
            }
        }while (choix !=0);
    }

    public void addContrat(){

        System.out.println("=== Types de Contrat Disponibles ===");
        TypeContrat[] typeContrat =  TypeContrat.values();
        for (int i=0 ; i < typeContrat.length;i++){
            System.out.println((i + 1) + " - " + typeContrat[i]);
        }
        System.out.print("Choisissez le type de contrat : ");
        int choixType = scanner.nextInt();
        scanner.nextLine();

        if (choixType < 1 || choixType > typeContrat.length) {
            System.out.println("Choix invalide !");
            return;
        }

        TypeContrat typeContratchoix = typeContrat[choixType-1];

        System.out.println("Entrez la date de début (format: yyyy-MM-dd) : ");
        String dateDebutStr = scanner.nextLine().trim();
        LocalDate dateDebut;
        try {
            dateDebut = LocalDate.parse(dateDebutStr);
        } catch (Exception e) {
            System.out.println("Date de début invalide !");
            return;
        }

        System.out.println("Entrez la date de fin (format: yyyy-MM-dd) : ");
        String datefinStr = scanner.nextLine().trim();
        LocalDate dateFin = null;
        if (!datefinStr.isEmpty()) {
            try {
                dateFin = LocalDate.parse(datefinStr);
            } catch (Exception e) {
                System.out.println("Date de début invalide !");
                return;
            }
        }

        ClientService clientService = new ClientService();
        Map<Integer, Client> clients = clientService.showAllClient();

        if (clients.isEmpty()) {
            System.out.println("Aucun client disponible !");
            return;
        }

        System.out.println("=== List Clients :");

        System.out.println("Choisissez l’ID du client pour ce contrat : ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        Optional<Client> clientOptional =clientService.findClientById(clientId);
        if (!clientOptional.isPresent()) {
            System.out.println("Client introuvable !");
            return;
        }
        Client client = clientOptional.get();

        Contrat contrat =  new Contrat(0,typeContratchoix,dateDebut,dateFin,client);
        contratService.addContrat(contrat);
    };

    public void deleteContrat(){

        System.out.println("Entre Id de Contrat :");
        int contratId = scanner.nextInt();
        scanner.nextLine();

        Optional<Contrat> contratOptional = contratService.showContratById(contratId);

        if (!contratOptional.isPresent()){
            System.out.println("Contrat introuvable  !");
            return;
        }
        contratService.deleteContrat(contratId);
        System.out.println("Contrat supprimé avec succès !");

    };

    public void findContratById(){
        System.out.println("Entre Id de Contrat :");
        int contratId = scanner.nextInt();
        scanner.nextLine();
        Optional<Contrat> contratOptional = contratService.showContratById(contratId);

        if (!contratOptional.isPresent()){
            System.out.println("Contrat introuvable  !");
            return;
        }
        Contrat contrat = contratOptional.get();
        System.out.println("Contrat trouvé : ");
        System.out.println("ID : " + contrat.getId());
        System.out.println("Type : " + contrat.getTypeContrat());
        System.out.println("Date Début : " + contrat.getDateDebut());
        System.out.println("Date Fin : " + contrat.getDateFin());
        if (contrat.getClient() != null) {
            System.out.println("Client : " + contrat.getClient().getNom() + " " + contrat.getClient().getPrenom());
        } else {
            System.out.println("Client : Aucun");
        }    };

    public void findContratByIdClient(){

        System.out.println("Entre Id de Client :");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        ClientService clientService =  new ClientService();
        Optional<Client> clientOptional = clientService.findClientById(clientId);
         if(!clientOptional.isPresent()){
             System.out.println("Client introuvable !");
             return;
         }
        List<Contrat> contrats = contratService.findContratsByClientId(clientId);

        if (contrats.isEmpty()) {
            System.out.println("Aucun contrat trouvé pour ce client.");
            return;
        }

        System.out.println("Contrats du client " + clientOptional.get().getNom() + " :");
        contrats.forEach(c -> {
            System.out.println("ID : " + c.getId() +
                    " | Type : " + c.getTypeContrat() +
                    " | Date Début : " + c.getDateDebut() +
                    " | Date Fin : " + c.getDateFin());
        });


    };

}
