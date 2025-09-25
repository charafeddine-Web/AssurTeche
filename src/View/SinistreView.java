package View;
import java.util.*;

public class SinistreView {

    Scanner scanner =  new Scanner(System.in);


    public void menu(){
        int choix;

        do {
            System.out.println("\n===== Gestion des Conseiller =====");
            System.out.println("1 - Ajouter un Conseiller");
            System.out.println("2 - Afficher Client D'un Conseiller");
            System.out.println("3 - Rechercher un Conseiller par ID");
            System.out.println("4 - Supprimer un Conseiller");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option : ");

            choix =scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:

                    break;
                case 0 : System.out.println("Au revoir");break;
                default : System.out.println("Option invalide !");break;
            }
        }while (choix !=0);
    }
}
