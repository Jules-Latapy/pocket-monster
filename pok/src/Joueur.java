import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {

    Objet[] objets;
    Monster[] monstres;
    Monster monstrePrincipal;

    //From command line
    public Joueur() {

        ArrayList<Objet> objets = new ArrayList<>(3);
        ArrayList<Monster> monsters = new ArrayList<>(3);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel objet voulez vous ?");
        System.out.println("\ta) potion");
        System.out.println("\tb) médicament");
        scanner.next();
    }

    public void changerMonstre() {

    }

    public void utiliserObjet() {

    }

    public void choisirAttaque() {

    }
}
