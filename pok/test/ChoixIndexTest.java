import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoixIndexTest {

    @Test
    void value() {
        assertEquals(new Choix.Index(0 ).value(), "a");
        assertEquals(new Choix.Index(25).value(), "z");
        assertEquals(new Choix.Index(26).value(), "aa");
        assertEquals(new Choix.Index(26*2).value(), "ba");

        //Choix.Index index = new Choix.Index(25);
        //for (int i = 0; i < 1000; i++) {
        //    index.increment();
        //    System.out.println(index.value()+" )");
        //}
    }
}