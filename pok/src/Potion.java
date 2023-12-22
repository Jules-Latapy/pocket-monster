public class Potion implements Objet {
    @Override
    public void consume(Monster monster) {
        monster.setLifePoint(monster.getLifePoint()+20);
    }
}
