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
            System.out.println("4 - Supprimer un client");
            System.out.println("5 - Return Au Menu Prancipal" );
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
                    deleteClient();
                    break;
                case 5:
                    return;
                case 0 : System.out.println("Au revoir");break;
                default : System.out.println("Option invalide !");break;
            }

        }while(choix !=0);
    }


    public void addClient(){
        ConseillerService conseillerSer = new  ConseillerService();

        System.out.println("Nom : ");
        String nom=scanner.nextLine();

        System.out.println("Prenom : ");
        String  prenom= scanner.nextLine();

        System.out.println("Email : ");
        String email = scanner.nextLine();

        Map<Integer,Conseiller> conseillers =conseillerSer.showAllConseiller();

        if(conseillers.values().isEmpty()){
            System.out.println(" Aucun conseiller disponible !");
            return;
        }
        System.out.println("\nListe des conseillers disponibles :");
        conseillers.values().forEach(c-> System.out.println("ID: " + c.getId() + " | " + c.getNom() + " " + c.getPrenom()));

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
    public void showAllClients(){

    }
    public void showClientById(){}
    public void deleteClient(){}
}
