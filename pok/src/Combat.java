public class Combat {

    /**
     * on admet ici que a.usage n'est pas déjà à zero
     * @param attaque attaque
     * @param attaquant attaquant
     * @param attaquee attaqué
     */
    public static void attaque(Attaque attaque, Monster attaquant, Monster attaquee, Terrain terrain) {

        if (Math.random() <= attaque.getProbaEchec()) {
            attaque.use();
            System.out.println(attaquant.getName()+" à échoué son attaque !");
            return;
        }

        double coef = 0.85 + (int)(Math.random() * ((1 - 0.85) + 1));

        double degat;

        double defense = attaquee.getDefence();

        if (attaquee.isCachee()) {
            defense*=2;
        }

        if (attaque.getNom().equals("main-nue")) {
            degat = 20 * (attaquant.getAttaque() /defense)*coef;
        }
        else {

            double avantage = 1;

            if (attaquee.getType().estFaible(attaque.getType())) {
                avantage = 0.5;
            }

            if (attaquee.getType().estFort(attaque.getType())) {
                avantage = 2;
            }

            degat = ((11*attaquant.getAttaque() * attaque.getPuissance()/25*defense)+2)*avantage*coef;

            attaque.use();
        }

        //-----partie ou l'attaque peut échoué a cause d'effet

        if (terrain.isEstInnondee() && attaquant.getType()!=Type.EAU) {
            if (Math.random() <= attaquee.getSpecialAttribut().get("Fall")) {
                attaquant.setLifePoint( attaquant.getLifePoint() - degat * 0.25 );
                System.out.println(attaquant.getName()+" à échoué son attaque, il est tombé !");
                return;
            }
        }

        if (attaquant.getEtat()==Etat.PARALYSER) {
            if (Math.random() <= 1d/4) { // une chance sur 4 de reussir son attaque
                System.out.println(attaquant.getName()+" n'a pas pu attaqué, il est paralysé !");
                return;
            }
        }

        //avec les different return, on considere une attaque échoué comme ne provoquant pas d'effet
        if (attaque.getType()!=Type.NORMAL)
            finAttaque(attaquant, attaquee, terrain);

        /*-----------------------------*/

        attaquee.setLifePoint( attaquee.getLifePoint() - degat);
    }

    /**
     * Effectue toutes ce qui doit etre fait a la fin d'une attaque,
     * notamment tout les changement d'etat
     * @param attaquant
     * @param attaquee
     */
    private static void finAttaque(Monster attaquant, Monster attaquee, Terrain terrain) {

        if (attaquant.getType() == Type.PLANTE && Math.random() <= attaquant.getSpecialAttribut().get("Heal")) {

            if (terrain.isEstInnondee()) {
                attaquant.setEtat(Etat.NORMAL);
                System.out.println(attaquant.getName() + " s'est soigné !");
                attaquant.setNbrTourEtat(0);
            }
        }

        if (!attaquant.isCachee()) {
            if (attaquant.getType() == Type.TERRE && Math.random() <= attaquant.getSpecialAttribut().get("Hide")) {
                attaquant.setCachee(true);
                System.out.println(attaquant.getName() + " s'est caché !");
                attaquant.setNbrTourEtat(0);
            }
        }

        if (!attaquee.getEtat().equals(Etat.BRULER)) {
            if (attaquant.getType() == Type.FEU && Math.random() <= attaquant.getSpecialAttribut().get("Burn")) {
                attaquee.setEtat(Etat.BRULER);
                System.out.println(attaquee.getName() + " brûle !");
                attaquee.setNbrTourEtat(0);
            }
        }

        if (!attaquee.getEtat().equals(Etat.EMPOISONNER)) {
            if (attaquant.getType() == Type.INSECTE && Math.random() <= attaquant.getSpecialAttribut().get("Poison")) {
                attaquee.setEtat(Etat.EMPOISONNER);
                System.out.println(attaquee.getName() + " est empoisonné !");
                attaquee.setNbrTourEtat(0);
            }
        }

        if (!attaquee.getEtat().equals(Etat.PARALYSER)) {
            if (attaquant.getType() == Type.ELECTRIC && Math.random() <= attaquant.getSpecialAttribut().get("Paralysis")) {
                attaquee.setEtat(Etat.PARALYSER);
                System.out.println(attaquee.getName() + " est paralysé !");
                attaquee.setNbrTourEtat(0);
            }
        }

        if (!terrain.isEstInnondee()) {
            if (attaquant.getType() == Type.EAU && Math.random() <= attaquant.getSpecialAttribut().get("Flood")) {
                System.out.println("le terrain est innondé !");
                terrain.setEstInnondee(true);
                terrain.setLanceur(attaquant);
                terrain.setNbrTourInonder(0);

                if (attaquant.getEtat()==Etat.BRULER)
                    attaquant.setEtat(Etat.NORMAL);

                if (attaquee.getEtat()==Etat.BRULER)
                    attaquee.setEtat(Etat.NORMAL);
            }
        }
    }
}
