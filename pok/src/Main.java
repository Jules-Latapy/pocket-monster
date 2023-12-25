public class Main {

    public static void main(String[] args) {
        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();

        while (true) {

            System.out.println("que voulez vous faire ?");
            System.out.print("\ta) attaquer");
            System.out.print("\tb) utiliser un objet");
            System.out.print("\tc) changer de monstre");

            //changements de monstres
            joueur1.changerMonstre();
            joueur2.changerMonstre();

            //objets utilisés
            joueur1.utiliserObjet();
            joueur2.utiliserObjet();

            //les monstres attaquent. Si les deux monstres attaquent, le monstre avec la plus grande vitesse attaque obtient l’initiative
            joueur1.choisirAttaque();
            joueur2.choisirAttaque();

            //if (attack1&attaqck2 != null) {
            //if vitesse1 > vitesse2
        }
    }
}