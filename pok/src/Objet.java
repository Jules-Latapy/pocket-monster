import java.util.function.Consumer;

public enum Objet {

    POMPE_A_EAU(monster -> {if (monster.getEtat().equals(Etat.MOUILLER)) monster.setEtat(Etat.NORMAL);}),
    ANTI_VENIN(monster -> {if (monster.getEtat().equals(Etat.EMPOISONNER)) monster.setEtat(Etat.NORMAL);}),
    PACK_DE_GLACE(monster -> {if (monster.getEtat().equals(Etat.BRULER)) monster.setEtat(Etat.NORMAL);}),
    PARATONNERE(monster -> {if (monster.getEtat().equals(Etat.PARALYSER)) monster.setEtat(Etat.NORMAL);}),
    POTION( monster -> monster.setLifePoint(monster.getLifePoint()+20));

    Consumer<Monster> fct ;

    Objet(Consumer<Monster> fct) {
        this.fct = fct;
    }

    void consume(Monster monster) {fct.accept(monster);}
}
