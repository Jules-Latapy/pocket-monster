public class Main {

    public static void main(String[] args) {
        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();

        while (true) {

            System.out.println("(Joueur 1)");
            Attaque attaque1 = getAction(joueur1);

            System.out.println("(Joueur 2)");
            Attaque attaque2 = getAction(joueur2);

            if (attaque1 != null && attaque2 != null) {

                if (joueur1.monstrePrincipal.getVitesse() == joueur2.monstrePrincipal.getVitesse()) {
                    if (Math.random()<0.5) {
                        Combat.attaque(attaque1, joueur1.monstrePrincipal, joueur2.monstrePrincipal);
                        Combat.attaque(attaque2, joueur2.monstrePrincipal, joueur1.monstrePrincipal);
                    }
                    else {
                        Combat.attaque(attaque2, joueur2.monstrePrincipal, joueur1.monstrePrincipal);
                        Combat.attaque(attaque1, joueur1.monstrePrincipal, joueur2.monstrePrincipal);
                    }
                }
                else
                    if (joueur1.monstrePrincipal.getVitesse() > joueur2.monstrePrincipal.getVitesse()) {
                        Combat.attaque(attaque1, joueur1.monstrePrincipal, joueur2.monstrePrincipal);
                        Combat.attaque(attaque2, joueur2.monstrePrincipal, joueur1.monstrePrincipal);
                    }
                    else {
                        Combat.attaque(attaque2, joueur2.monstrePrincipal, joueur1.monstrePrincipal);
                        Combat.attaque(attaque1, joueur1.monstrePrincipal, joueur2.monstrePrincipal);
                    }
            }

        }
    }

    private static Attaque getAction(Joueur joueur) {
        Choix action = new Choix("que voulez vous faire ?", new String[]{
                "attaquer",
                "utiliser un objet",
                "changer de monstre"
        }, false);

        Attaque attaque1 = null;
        switch (action.getInput()) {
            case "attaquer" -> {return joueur.choisirAttaque();}
            case "utiliser un objet" -> {joueur.changerMonstre();}
            case "changer de monstre" -> {joueur.utiliserObjet();}
        }

        return null;
    }
}