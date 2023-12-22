import java.util.Map;

public final class Monster {
    private final Attaque[] attaques;
    private final double defense;
    private final int vitesse;
    private final Type type;
    private final double attaque;
    private double lifePoint;

    private Etat etat = Etat.NORMAL;

    private int nbrTourEtat = 0;
    private Map<String, Double> specialAttribut;
    public Monster(
            Attaque[] attaques,
            double lifePoint,
            double defense,
            double attaque,
            int vitesse,
            Type type,
            Map<String, Double> specialAttribut
    ) {
        this.attaques = attaques;
        this.lifePoint = lifePoint;
        this.defense = defense;
        this.attaque = attaque;
        this.vitesse = vitesse;
        this.type = type;
        this.specialAttribut = specialAttribut;
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

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Type getType() {
        return type;
    }

    public double getAttaque() {
        return attaque;
    }

    public void setLifePoint(double lifePoint) {
        this.lifePoint=lifePoint;
    }

    public Map<String, Double> getSpecialAttribut() {
        return specialAttribut;
    }
}