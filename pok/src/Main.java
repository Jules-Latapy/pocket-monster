import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();

        while (!joueur1.asLost() && !joueur2.asLost()) {

            System.out.println("(Joueur 1)");
            Attaque attaque1 = getAction(joueur1);

            System.out.println("(Joueur 2)");
            Attaque attaque2 = getAction(joueur2);

            if (attaque1 != null && attaque2 != null)

                if (joueur1.getMonstrePrincipal().getVitesse() == joueur2.getMonstrePrincipal().getVitesse())

                    if (Math.random()<0.5) {
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal());
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal());
                    }
                    else {
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal());
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal());
                    }
                else
                    if (joueur1.getMonstrePrincipal().getVitesse() > joueur2.getMonstrePrincipal().getVitesse()) {
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal());
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal());
                    }
                    else {
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal());
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal());
                    }

            System.out.println("Joueur 1 :"+joueur1.getMonstrePrincipal().getName() + ", " + joueur1.getMonstrePrincipal().getLifePoint());
            System.out.println("Joueur 2 :"+joueur2.getMonstrePrincipal().getName() + ", " + joueur2.getMonstrePrincipal().getLifePoint());

            suppressMainPokemonIfDead(joueur1);
            suppressMainPokemonIfDead(joueur2);
        }

        System.out.println("le joueur "+(joueur1.asLost()?"1":"2") + " a perdu !");
    }

    private static void suppressMainPokemonIfDead(Joueur joueur) {
        if (joueur.getMonstrePrincipal().getLifePoint()>0) {
            return;
        }

        System.out.println(joueur.getMonstrePrincipal().getName() + " est KO!");

        joueur.getMonstres().remove(joueur.getMonstrePrincipal());

        if (!joueur.getMonstres().isEmpty()) {
            joueur.changerMonstre();
        }
    }

    private static Attaque getAction(Joueur joueur) {

        List<String> possibilities = new ArrayList<>(3);

        if (!joueur.getObjets().isEmpty()) {
            possibilities.add("utiliser un objet");
        }

        if (joueur.getMonstres().size()!=1) {
            possibilities.add("changer de monstre");
        }

        possibilities.add("attaquer");

        Choix action = new Choix("que voulez vous faire ?", possibilities, false);

        switch (action.getInput()) {
            case "attaquer" -> {return joueur.choisirAttaque();}
            case "utiliser un objet" -> joueur.changerMonstre();
            case "changer de monstre" -> joueur.utiliserObjet();
        }

        return null;
    }
}