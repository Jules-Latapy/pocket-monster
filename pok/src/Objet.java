import java.util.function.BiConsumer;

public enum Objet {

    POMPE_A_EAU((monster, terrain) -> {if (terrain.isEstInnondee()) terrain.setEstInnondee(false);}),
    ANTI_VENIN((monster, __) -> {if (monster.getEtat().equals(Etat.EMPOISONNER)) monster.setEtat(Etat.NORMAL);}),
    PACK_DE_GLACE((monster, __) -> {if (monster.getEtat().equals(Etat.BRULER)) monster.setEtat(Etat.NORMAL);}),
    PARATONNERRE((monster, __) -> {if (monster.getEtat().equals(Etat.PARALYSER)) monster.setEtat(Etat.NORMAL);}),
    POTION((monster, __) -> monster.setLifePoint(monster.getLifePoint()+20));

    private final BiConsumer<Monster, Terrain> fct ;

    Objet(BiConsumer<Monster, Terrain> fct) {
        this.fct = fct;
    }

    public void consume(Monster monster, Terrain terrain) {fct.accept(monster, terrain);}

    public String toSentenceLike() {
        return this.toString().toLowerCase().replaceAll("_", " ");
    }

    public static Objet fromSentenceLike(String s) {
        return Objet.valueOf(s.toUpperCase().replaceAll(" ", "_"));
    }
}
