public class Combat {

    /**
     * on admet ici que a.usage n'est pas déjà à zero
     * @param attaque attaque
     * @param attaquant attaquant
     * @param attaquee attaqué
     */
    public static void attaque(Attaque attaque, Monster attaquant, Monster attaquee) {

        if (Math.random() <= attaque.getProbaEchec()) {
            attaque.use();
            System.out.println(attaquant.getName()+" à échoué son attaque !");
            return;
        }

        double coef = 0.85 + (int)(Math.random() * ((1 - 0.85) + 1));

        double degat;

        double defense = attaquee.getDefence();

        if (attaquee.getEtat()==Etat.CACHER) {
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

        if (attaquant.getEtat()==Etat.MOUILLER && attaquant.getType()!=Type.EAU) {
            if (Math.random() <= attaquee.getSpecialAttribut().get("Fall")) {
                attaquant.setLifePoint( attaquant.getLifePoint() - degat * 0.25 );
                System.out.println(attaquant.getName()+" à échoué son attaque, il est tombé !");
                return;
            }
        }

        if (attaquant.getEtat()==Etat.PARALYSER) {
            if (Math.random() <= attaquee.getSpecialAttribut().get("Paralysis")) {
                System.out.println(attaquant.getName()+" n'a pas pu attaqué, il est paralysé !");
                return;
            }
        }

        finAttaque(attaquant, attaquee);

        /*-----------------------------*/

        attaquee.setLifePoint( attaquee.getLifePoint() - degat);
    }

    private static void finAttaque(Monster attaquant, Monster attaquee) {

        if (attaquant.getType() == Type.PLANTE && Math.random() <= attaquant.getSpecialAttribut().get("Heal")) {

            if (attaquant.getEtat()!=Etat.MOUILLER)
                attaquant.setEtat(Etat.NORMAL);

            System.out.println(attaquant.getName()+" s'est soigné !");
            attaquant.setNbrTourEtat(0);
        }

        if (attaquant.getType() == Type.TERRE && Math.random() <= attaquant.getSpecialAttribut().get("Hide")) {
            attaquant.setEtat(Etat.CACHER);
            System.out.println(attaquant.getName()+" s'est caché !");
            attaquant.setNbrTourEtat(0);
        }

        if (attaquant.getType() == Type.FEU && Math.random() <= attaquant.getSpecialAttribut().get("Burn")) {
            attaquee.setEtat(Etat.BRULER);
            System.out.println(attaquee.getName()+" brûle !");
            attaquee.setNbrTourEtat(0);
        }
    }
}
