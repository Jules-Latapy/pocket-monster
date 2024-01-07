import java.util.Map;

public final class Monster {
    private Attaque[] attaques;

    private final double defense;
    private final int vitesse;
    private final Type type;
    private final double attaque;
    private final Map<String, Double> specialAttribut;
    private final String name;

    private double lifePoint;

    private Etat etat = Etat.NORMAL;

    private int nbrTourEtat = 0;

    //caché n'est pas dans l'Enum "Etat"
    //car il est additionnable à un état
    boolean cachee = false;

    int nbrTourCachee = 0;

    public Monster(
            String name,
            double lifePoint,
            double defense,
            double attaque,
            int vitesse,
            Type type,
            Map<String, Double> specialAttribut
    ) {
        this.name            = name;
        this.lifePoint       = lifePoint;
        this.defense         = defense;
        this.attaque         = attaque;
        this.vitesse         = vitesse;
        this.type            = type;
        this.specialAttribut = specialAttribut;
    }

    public String getName() {
        return name;
    }

    public double getLifePoint() {
        return lifePoint;
    }

    public Attaque[] getAttaques() {
        return attaques;
    }
    public void setAttaques(Attaque[] attaques) {
        this.attaques = attaques;
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

    public int getNbrTourEtat() {
        return nbrTourEtat;
    }

    public void setNbrTourEtat(int nbrTourEtat) {
        this.nbrTourEtat = nbrTourEtat;
    }

    public boolean isCachee() {
        return cachee;
    }

    public void setCachee(boolean cachee) {
        this.cachee = cachee;
    }

    public int getNbrTourCachee() {
        return nbrTourCachee;
    }

    public void setNbrTourCachee(int nbrTourCachee) {
        this.nbrTourCachee = nbrTourCachee;
    }
}