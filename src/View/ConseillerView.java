package View;

import model.Conseiller;
import service.ConseillerService;

import java.util.Scanner;

public class ConseillerView {
    private  final ConseillerService conseillerService;
    private final Scanner scanner;

    public ConseillerView(){
        this.conseillerService = new ConseillerService();
        this.scanner = new Scanner(System.in);
    }

    public void menu(){
        int choix;

        do {
            System.out.println("\n===== Gestion des Conseiller =====");
            System.out.println("1 - Ajouter un Conseiller");
            System.out.println("2 - Afficher Client D'un Conseiller");
            System.out.println("3 - Rechercher un Conseiller par ID");
            System.out.println("4 - Supprimer un Conseiller");
            System.out.println("5 - Afficher Tout Les Conseillers");
            System.out.println("6 - Return Au Menu Prancipal ");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option : ");

            choix =scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    addConseiller();
                    break;
                case 2:
                    showClientDunConseiller();
                    break;
                case 3:
                    showConseillerById();
                    break;
                case 4:
                    deleteConseiller();
                    break;
                case 5:
                    showAllConseiller();
                    break;
                case 6:
                    return;
                case 0 : System.out.println("Au revoir");break;
                default : System.out.println("Option invalide !");break;
            }
        }while (choix !=0);
    }

    public void addConseiller(){
        System.out.println("Nom : ");
        String nom=scanner.nextLine();

        System.out.println("Prenom : ");
        String  prenom= scanner.nextLine();

        System.out.println("Email : ");
        String email = scanner.nextLine();

        Conseiller conseiller =  new Conseiller(0,nom,prenom,email);
        conseillerService.addConseiller(conseiller);

        System.out.println(" Conseiller ajouté avec succès !");
    }
    public void showAllConseiller(){
        conseillerService.showAllConseiller();
    };
    public void showClientDunConseiller(){}
    public void showConseillerById(){}
    public void deleteConseiller(){}
}
