class LecteurTest {

    @org.junit.jupiter.api.Test
    void getListMonster() {
        Lecteur.getListMonster("../pok/test/rsrc/pokemon-data.txt");
    }

    @org.junit.jupiter.api.Test
    void getListAttack() {
        Lecteur.getListAttack("../pok/test/rsrc/attack-data.txt");
    }
}