package View;

import java.util.Scanner;
import View.*;

public class UI {

    ConseillerView  conseiller = new ConseillerView();
    ClientView client = new ClientView();
    ContratView contrat =  new ContratView();
    SinistreView sinistre = new SinistreView();

    Scanner scanner= new Scanner(System.in);
    int choix ;
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n===== Assurance Tech =====");
            System.out.println("1 - Gestion des Conseillers");
            System.out.println("2 - Gestion des Clients");
            System.out.println("3 - Gestion des Contrats");
            System.out.println("4 - Gestion des Sinistres");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    conseiller.menu();
                    break;
                case 2:
                    client.menu();
                    break;
                case 3:
                    contrat.menu();
                    break;
                case 4:
                    sinistre.menu();
                    break;
                case 0:
                    System.out.println("Au revoir ");
                    break;
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        } while (choix != 0);

        scanner.close();
    }
}
