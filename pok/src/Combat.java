public class Combat {

    /**
     * on admet ici que a.usage n'est pas déjà à zero
     * @param attaque attaque
     * @param attaquant attaquant
     * @param attaquee attaqué
     */
    public static void attaque(Attaque attaque, Monster attaquant, Monster attaquee) {

        if ((int)(Math.random() * 101) <= attaque.getProbaEchec()) {
            attaque.use();
            return;
        }

        double coef = 0.85 + (int)(Math.random() * ((1 - 0.85) + 1));

        double degat;

        double defense = attaquee.getDefence();

        if (attaquee.getEtat()==Etat.CACHER) {
            defense*=2;
        }

        if (attaque.getNom().equals("main nue")) {
            degat = 20 * (attaque.getPuissance() /defense)*coef;
        }
        else {

            double avantage = 1;

            if (attaquee.getType().estFaible(attaque.getType())) {
                avantage = 0.5;
            }

            if (attaquee.getType().estFort(attaque.getType())) {
                avantage = 2;
            }

            degat = 20 * ((11*attaque.getPuissance()*attaquant.getAttaque() /25*defense)+2)*avantage*coef;

            attaque.use();
        }

        if (attaquant.getEtat()==Etat.MOUILLER && attaquant.getType()!=Type.WATER) {
            if ((int)(Math.random() * 101) <= attaquee.getSpecialAttribut().get("Fall")) {
                attaquant.setLifePoint( attaquant.getLifePoint() - degat * 0.25 );
                return;
            }
        }

        if (attaquant.getEtat()==Etat.PARALYSER) {
            if ((int)(Math.random() * 101) <= attaquee.getSpecialAttribut().get("Paralysis")) {
                return;
            }
        }
        /*-----------------------------*/

        attaquee.setLifePoint( attaquee.getLifePoint() - degat);
    }
}
