public class Combat {

    /**
     * on admet ici que a.usage n'est pas déjà à zero
     * @param a
     * @param m1 attaquant
     * @param m2 attaqué
     */
    public void attaque(Attaque a, Monster m1, Monster m2) {

        if ((int)(Math.random() * 101) <= a.getProbaEchec()) {
            return;
        }

        double coef = 0.85 + (int)(Math.random() * ((1 - 0.85) + 1));

        double degat;

        if (a.getNom().equals("main nue")) {
            degat = 20 * (a.getPuissance() /m2.getDefence())*coef;
        }
        else {

            double avantage = 1;

            if (m2.getType().estFaible(a.getType())) {
                avantage = 0.5;
            }

            if (m2.getType().estFort(a.getType())) {
                avantage = 2;
            }

            degat = 20 * ((11*a.getPuissance()*m1.getAttaque() /25*m2.getDefence())+2)*avantage*coef;

            //TODO
            m1.getCapaciteSpecial();

            a.use();
        }

        /*-----------------------------*/

        m1.getLifePoint( m1.getLifePoint() - degat);

        //todo : empoisonnement

    }
}
