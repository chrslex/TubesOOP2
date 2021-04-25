import oopokemon.exception.NotInitializedException;
import oopokemon.map.*;
import oopokemon.occupier.*;
import oopokemon.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    Enemy en1;
    Enemy en2;

    @BeforeEach
    void setUp() throws NotInitializedException {
        Map m = new Map();
        en1 = new Enemy(m, 1, 1);
        en2 = new Enemy(m, 5, 3);
    }

//    @Test
//    void init() {
//    }

    @Test
    void setPos() {
        assertFalse(en1.setPos(33, 26));
        assertTrue(en1.setPos(24, 14));
    }

//    @Test
//    void setEngimon() {
//    }

    @Test
    void getEngimon() {
        Squirtle s = new Squirtle();
        Inferail i = new Inferail();
        Dragon d = new Dragon();

        assertEquals(s, en1.getEngimon());
        en1.setEngimon(d);
        assertEquals(d, en1.getEngimon());
        en1.setEngimon(s);
        assertEquals(s, en1.getEngimon());
        assertEquals(i, en2.getEngimon());
    }

    @Test
    void getExp() {
        assertEquals(0, en1.getExp());
        assertEquals(0, en2.getExp());

        assertTrue(en1.setExp(1000));
        assertEquals(1000, en1.getExp());
        assertFalse(en1.setExp(100));
        assertEquals(2000, en1.getExp());

        assertTrue(en2.setExp(1999));
        assertEquals(1999, en2.getExp());
    }

//    @Test
//    void setExp() {
//    }

    @Test
    void move() {
        assertTrue(en1.move(0));
        assertFalse(en1.move(-1));
        assertFalse(en2.move(4));
    }
}
