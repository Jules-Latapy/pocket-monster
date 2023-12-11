public class Combat {

    /**
     * on admet ici que a.usage n'est pas déjà à zero
     * @param a
     * @param m1 attaquant
     * @param m2 attaqué
     */
    void attaque(Attaque a, Monster m1, Monster m2) {

        //a.probaEchec;

        /*-----------------------------*/

        double coef = 0.85 + (int)(Math.random() * ((1 - 0.85) + 1));;
        double degat = 20 * ((double) a.puissance /m2.defense)*coef;

        a.usage--;

        if (m2.type.estFaible(a.type)) {
            m1.lifePoint -= degat*0.5;
            return;
        }

        if (m2.type.estFort(a.type)) {
            m1.lifePoint -= degat*2;
            return;
        }

        m1.lifePoint -= degat;
    }
}
