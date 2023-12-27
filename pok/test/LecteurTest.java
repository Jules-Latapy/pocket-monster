class LecteurTest {

    @org.junit.jupiter.api.Test
    void getListMonster() {
        Lecteur.getListMonster("./test/rsrc/pokemon-data.txt");
    }

    @org.junit.jupiter.api.Test
    void getListAttack() {
        Lecteur.getListAttack("./test/rsrc/attack-data.txt");
    }
}