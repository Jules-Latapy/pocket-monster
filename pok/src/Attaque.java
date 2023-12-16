public final class Attaque {
    private final String nom;
    private final Type type;
    private final double puissance;
    private int usage;
    private final int probaEchec;
    private final int vitesse;

    public Attaque(
            String nom,
            Type type,
            double puissance,
            int usage,
            int probaEchec,
            int vitesse) {
        this.nom = nom;
        this.type = type;
        this.puissance = puissance;
        this.usage = usage;
        this.probaEchec = probaEchec;
        this.vitesse = vitesse;
    }

    public void use() {
        usage--;
    }

    public String getNom() {
        return nom;
    }

    public Type getType() {
        return type;
    }

    public double getPuissance() {
        return puissance;
    }

    public int getUsage() {
        return usage;
    }

    public int getProbaEchec() {
        return probaEchec;
    }

    public int getVitesse() {
        return vitesse;
    }
}