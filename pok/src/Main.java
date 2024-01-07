import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Terrain terrain = new Terrain();

        Joueur joueur1 = new Joueur(terrain);
        Joueur joueur2 = new Joueur(terrain);

        while (!joueur1.asLost() && !joueur2.asLost()) {

            debutTour(joueur1);
            debutTour(joueur2);

            System.out.println("(Joueur 1)");
            Attaque attaque1 = getAction(joueur1);

            System.out.println("(Joueur 2)");
            Attaque attaque2 = getAction(joueur2);

            if (attaque1 != null && attaque2 != null)

                if (joueur1.getMonstrePrincipal().getVitesse() == joueur2.getMonstrePrincipal().getVitesse())

                    if (Math.random()<0.5) {
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal(), terrain);
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal(), terrain);
                    }
                    else {
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal(), terrain);
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal(), terrain);
                    }
                else
                    if (joueur1.getMonstrePrincipal().getVitesse() > joueur2.getMonstrePrincipal().getVitesse()) {
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal(), terrain);
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal(), terrain);
                    }
                    else {
                        Combat.attaque(attaque2, joueur2.getMonstrePrincipal(), joueur1.getMonstrePrincipal(), terrain);
                        Combat.attaque(attaque1, joueur1.getMonstrePrincipal(), joueur2.getMonstrePrincipal(), terrain);
                    }

            System.out.println("Joueur 1 :"+joueur1.getMonstrePrincipal().getName() + ", " + joueur1.getMonstrePrincipal().getLifePoint());
            System.out.println("Joueur 2 :"+joueur2.getMonstrePrincipal().getName() + ", " + joueur2.getMonstrePrincipal().getLifePoint());

            suppressMainPokemonIfDead(joueur1);
            suppressMainPokemonIfDead(joueur2);
            live(joueur1);
            live(joueur2);
            live(terrain);
            endTour(terrain, joueur1, joueur2);
        }

        System.out.println("le joueur "+(joueur1.asLost()?"1":"2") + " a perdu !");
    }

    /**
     * on vient incrémenter les differentes variables necessaires.
     * @param joueur
     */
    private static void live(Joueur joueur) {
        for (Monster monstre : joueur.getMonstres()) {
            monstre.setNbrTourEtat(monstre.getNbrTourEtat()+1);
            if (monstre.isCachee())
                monstre.setNbrTourCachee(monstre.getNbrTourCachee()+1);
        }
    }

    private static void live(Terrain terrain) {
        terrain.setNbrTourInonder(terrain.getNbrTourInonder()+1);
    }

    private static void endTour(Terrain terrain,Joueur lanceurOwner1, Joueur lanceurOwner2) {
        if (terrain.isEstInnondee()) {
            if (lanceurOwner1.getMonstrePrincipal() != terrain.getLanceur() &&
                lanceurOwner2.getMonstrePrincipal() != terrain.getLanceur()) {
                terrain.setEstInnondee(false);
                terrain.setNbrTourInonder(0);
            } else {
                //au premier tour 1/3 de chance de s'arreter
                //au deuxieme tour 2/3
                //au troisieme on arrete
                if (Math.random() <= terrain.getNbrTourInonder() / 3d) {
                    terrain.setEstInnondee(false);
                    terrain.setNbrTourInonder(0);
                }
            }
        }

        //TODO enlever repetition
        if (lanceurOwner1.getMonstrePrincipal().isCachee()) {
            if (Math.random() <= lanceurOwner1.getMonstrePrincipal().getNbrTourEtat()/3d){
                lanceurOwner1.getMonstrePrincipal().setCachee(false);
                lanceurOwner1.getMonstrePrincipal().setNbrTourEtat(0);
            }
        }

        if (lanceurOwner2.getMonstrePrincipal().isCachee()) {
            if (Math.random() <= lanceurOwner2.getMonstrePrincipal().getNbrTourEtat()/3d){
                lanceurOwner2.getMonstrePrincipal().setCachee(false);
                lanceurOwner2.getMonstrePrincipal().setNbrTourEtat(0);
            }
        }
    }

    private static void debutTour(Joueur joueur1) {

        if (joueur1.getMonstrePrincipal().getEtat()==Etat.PARALYSER) {
            if (Math.random() <= joueur1.getMonstrePrincipal().getNbrTourEtat()/6d){
                joueur1.getMonstrePrincipal().setEtat(Etat.NORMAL);
                joueur1.getMonstrePrincipal().setNbrTourEtat(0);
            }
        }
        //plante +0.2% si innondé
        if (joueur1.getMonstrePrincipal().getType().isNature()) {
            if (joueur1.getTerrain().isEstInnondee()) {
                joueur1.getMonstrePrincipal().setLifePoint(joueur1.getMonstrePrincipal().getLifePoint()*1.05);
            }
        }
        //1/10 attaque d'un pokémon brulé/empoisonné
        if (joueur1.getMonstrePrincipal().getEtat()==Etat.BRULER ||
            joueur1.getMonstrePrincipal().getEtat()==Etat.EMPOISONNER) {

            joueur1.getMonstrePrincipal().setLifePoint(joueur1.getMonstrePrincipal().getLifePoint()-0.1*joueur1.getMonstrePrincipal().getAttaque());
        }
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
            case "utiliser un objet" ->  joueur.utiliserObjet();
            case "changer de monstre" -> joueur.changerMonstre();
        }

        return null;
    }
}