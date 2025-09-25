package View;
import java.util.*;

import model.Client;
import model.Conseiller;
import service.ClientService;
import service.ConseillerService;

public class ClientView {
    private final ClientService clientService;
    private final Scanner scanner;

    public ClientView(){
        this.clientService = new ClientService();
        this.scanner = new Scanner(System.in);
    }

    public void menu(){
        int choix;
        do {
            System.out.println("\n===== Gestion des Clients =====");
            System.out.println("1 - Ajouter un client");
            System.out.println("2 - Afficher tous les clients");
            System.out.println("3 - Rechercher un client par ID");
            System.out.println("4 - Rechercher un client par Nom trier ");
            System.out.println("5 - Afficher Clients d'un Conseiller ");
            System.out.println("6 - Supprimer un client");
            System.out.println("7 - Return Au Menu Prancipal" );
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option : ");

            choix =scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    addClient();
                    break;
                case 2:
                    showAllClients();
                    break;
                case 3:
                    showClientById();
                    break;
                case 4:
                    showClientByNomAndPrenom();
                    break;
                case 5:
                    afficherClientsParConseiller();
                    break;
                case 6:
                    deleteClient();
                    break;
                case 7:
                    return;
                case 0 : System.out.println("Au revoir");break;
                default : System.out.println("Option invalide !");break;
            }

        }while(choix !=0);
    }


    public void addClient(){
        ConseillerService conseillerSer = new  ConseillerService();

        System.out.println("Nom : ");
        String nom=scanner.nextLine().trim();
        if (nom.isEmpty() || !nom.matches("[a-zA-Z]+")) {
            System.out.println(" Nom invalide ! (Seulement lettres et pas vide)");
            return;
        }

        System.out.println("Prenom : ");
        String  prenom= scanner.nextLine().trim();
        if (prenom.isEmpty() || !prenom.matches("[a-zA-Z]+")) {
            System.out.println("Prénom invalide ! (Seulement lettres et pas vide)");
            return;
        }

        System.out.println("Email : ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Email invalide !");
            return;
        }

        System.out.println("\nListe des conseillers disponibles :");
        Map<Integer,Conseiller> conseillers =conseillerSer.showAllConseiller();

        if(conseillers.values().isEmpty()){
            System.out.println(" Aucun conseiller disponible !");
            return;
        }

        System.out.print("Entrez l'ID du conseiller pour ce client : ");
        int conseillerid=scanner.nextInt();
        scanner.nextLine();

        Optional<Conseiller>  conseilleropt=  conseillerSer.findConseillerById(conseillerid);

        if(!conseilleropt.isPresent()){
            System.out.println(" Conseiller introuvable !");
            return;
        }

        Conseiller conseiller = conseilleropt.get();

        Client client =  new Client(0,nom,prenom,email,conseiller);
        clientService.addClient(client);
          System.out.println(" Client ajouté avec succès !");
    }
    public void showAllClients() {
        Map<Integer, Client> clients = clientService.showAllClient();

        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé !");
            return;
        }

        System.out.println("\n--- Liste des Clients ---");
        clients.values().forEach(c -> System.out.println(
                c.getId() + " - " + c.getNom() + " " + c.getPrenom() + " | " + c.getEmail()
        ));
    }    public void deleteClient(){

        System.out.println("Entre le ID de Client :");
        int idClient=scanner.nextInt();
        scanner.nextLine();
        Optional<Client> clientServicetrv= clientService.findClientById(idClient);

        if(!clientServicetrv.isPresent()){
            System.out.println("Client introuvable ! ");
            return;
        }

        Client client=clientServicetrv.get();
        clientService.deleteClient(client.getId());

        System.out.println("Conseiller supprimé avec succès !");

    }
    public void showClientById(){
        System.out.println("Entre le ID de Client :");
        int idClient=scanner.nextInt();
        scanner.nextLine();

        Optional<Client> clientOpt = clientService.findClientById(idClient);

        if (!clientOpt.isPresent()) {
            System.out.println("Client introuvable !");
            return;
        }
        Client client = clientOpt.get();
        System.out.println("Client trouvé : ");
        System.out.println("ID : " + client.getId() +
                " | Nom : " + client.getNom() +
                " | Prénom : " + client.getPrenom() +
                " | Email : " + client.getEmail() +
                " | Conseiller : " +
                (client.getConseiller() != null
                        ? client.getConseiller().getNom() + " " + client.getConseiller().getPrenom()
                        : "Aucun conseiller"));
    }
    public void showClientByNomAndPrenom() {
        System.out.println("Entrez le nom du client : ");
        String nom = scanner.nextLine().trim();

        List<Client> clientsFiltres = clientService.findClientsByNomPrenom(nom);

        if (clientsFiltres.isEmpty()) {
            System.out.println("Aucun client trouvé avec le nom : " + nom );
            return;
        }

        System.out.println("\n--- Clients trouvés ---");
        clientsFiltres.forEach(c -> System.out.println(
                "ID : " + c.getId() +
                        " | Nom : " + c.getNom() +
                        " | Prénom : " + c.getPrenom() +
                        " | Email : " + c.getEmail() +
                        " | Conseiller : " +
                        (c.getConseiller() != null
                                ? c.getConseiller().getNom() + " " + c.getConseiller().getPrenom()
                                : "Aucun conseiller")
        ));
    }
    public void afficherClientsParConseiller() {
        System.out.println("Entre le ID de Conseiller :");
        int conseillerId=scanner.nextInt();
        scanner.nextLine();

        List<Client> clients = clientService.showListClientByConseiller(conseillerId);

        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé pour ce conseiller.");
        } else {
            System.out.println("Liste des clients pour le conseiller " + conseillerId + " :");
            clients.forEach(c ->
                    System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom() + " | " + c.getEmail())
            );
        }
    }


}
