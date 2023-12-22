import static org.junit.jupiter.api.Assertions.*;

class LecteurTest {

    @org.junit.jupiter.api.Test
    void getListMonster() {
        Lecteur.getListMonster("../pok/test/rsrc/pokemon-data.txt");
    }
}