import java.util.function.Consumer;

public enum Objet {

    POMPE_A_EAU(monster -> {if (monster.getEtat().equals(Etat.MOUILLER)) monster.setEtat(Etat.NORMAL);}),
    ANTI_VENIN(monster -> {if (monster.getEtat().equals(Etat.EMPOISONNER)) monster.setEtat(Etat.NORMAL);}),
    PACK_DE_GLACE(monster -> {if (monster.getEtat().equals(Etat.BRULER)) monster.setEtat(Etat.NORMAL);}),
    PARATONNERRE(monster -> {if (monster.getEtat().equals(Etat.PARALYSER)) monster.setEtat(Etat.NORMAL);}),
    POTION( monster -> monster.setLifePoint(monster.getLifePoint()+20));

    private final Consumer<Monster> fct ;

    Objet(Consumer<Monster> fct) {
        this.fct = fct;
    }

    public void consume(Monster monster) {fct.accept(monster);}

    public String toSentenceLike() {
        return this.toString().toLowerCase().replaceAll("_", " ");
    }

    public static Objet fromSentenceLike(String s) {
        return Objet.valueOf(s.toUpperCase().replaceAll(" ", "_"));
    }
}
