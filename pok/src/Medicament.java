public class Medicament implements Objet{
    @Override
    public void consume(Monster monster) {
        monster.setEtat(Etat.NORMAL);
    }
}
