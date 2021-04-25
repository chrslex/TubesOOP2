import oopokemon.exception.NotInitializedException;
import oopokemon.map.*;
import oopokemon.occupier.ActiveEngimon;
import oopokemon.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveEngimonTest {
    private Engimon e1;
    private Raichu e2;
    private ActiveEngimon ae1;
    private ActiveEngimon ae2;

    @BeforeEach
    void setUp() throws NotInitializedException {
        Map m1 = new Map();
        e1 = new Engimon();
        e2 = new Raichu();
        ae1 = new ActiveEngimon(m1);
        ae2 = new ActiveEngimon(8, 14, m1, e1);
    }

//    @Test
//    void getLife() {
//        assertEquals(0, ae1.getLife());
//        assertEquals(3, ae2.getLife());
//    }
//
//    @Test
//    void getLevel() {
//        assertEquals(1, ae1.getLevel());
//        assertEquals(1, ae2.getLevel());
//    }
//
//    @Test
//    void getEngimon() {
//        assertNull(ae1.getEngimon());
//        assertEquals(e1, ae2.getEngimon());
//    }
//
//    @Test
//    void setEngimon() {
//        assertNull(ae1.getEngimon());
//        ae1.setEngimon(e2);
//        assertEquals(e2, ae1.getEngimon());
//        assertEquals(e1, ae2.getEngimon());
//    }
}
