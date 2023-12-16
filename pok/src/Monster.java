public final class Monster {
    private final Attaque[] attaques;
    private final double defense;
    private final int vitesse;
    private final int capaciteSpecial;
    private final Type type;
    private final double attaque;
    private double lifePoint;
    private Etat etat;
    private int nbrTourEtat;

    public Monster(
            Attaque[] attaques,
            double defense,
            int vitesse,
            int capaciteSpecial,
            Type type,
            double attaque,
            double lifePoint) {
        this.attaques = attaques;
        this.defense = defense;
        this.vitesse = vitesse;
        this.capaciteSpecial = capaciteSpecial;
        this.type = type;
        this.attaque = attaque;
        this.lifePoint = lifePoint;
    }

    public double getLifePoint() {
        return lifePoint;
    }

    public Attaque[] getAttaques() {
        return attaques;
    }

    public double getDefence() {
        return defense;
    }

    public int getVitesse() {
        return vitesse;
    }

    public Etat getEtat() {
        return etat;
    }

    public int getCapaciteSpecial() {
        return capaciteSpecial;
    }

    public Type getType() {
        return type;
    }

    public double getAttaque() {
        return attaque;
    }

    public void getLifePoint(double lifePoint) {
        this.lifePoint=lifePoint;
    }
}