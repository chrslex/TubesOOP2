import oopokemon.exception.NotInitializedException;
import oopokemon.map.*;
import oopokemon.occupier.*;
import oopokemon.species.*;
import oopokemon.element.*;
import oopokemon.skill.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player p;

    @BeforeEach
    void setUp() throws NotInitializedException {
        Map m = new Map();
        p = new Player(m);
    }

//    @Test
//    void getLevel() {
//    }
//
//    @Test
//    void getLife() {
//    }
//
//    @Test
//    void getHighestLevel() {
//    }
//
//    @Test
//    void setActiveEngimon() {
//    }
//
//    @Test
//    void openInventory() {
//    }
//
//    @Test
//    void getInventory() {
//    }
//
//    @Test
//    void getEngimon() {
//    }
//
//    @Test
//    void getActiveEngimon() {
//    }
//
//    @Test
//    void breeding() {
//    }
//
//    @Test
//    void getClosestEnemy() {
//    }
//
//    @Test
//    void setPositionOcc() {
//    }
}
