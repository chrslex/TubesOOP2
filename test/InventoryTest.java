import oopokemon.inventory.*;
import oopokemon.skill.*;
import oopokemon.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.security.provider.Sun;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory i;
    private Skill s1;
    private Torrent s2;
    private Fissure s3;
    private Engimon e1;
    private Kyogre e2;
    private Excadrill e3;

    @BeforeEach
    void setUp() {
        i = new Inventory();
        s1 = new Skill();
        s2 = new Torrent();
        s3 = new Fissure();
        e1 = new Engimon();
        e2 = new Kyogre();
        e3 = new Excadrill();
    }

    @Test
    void isSkillExist() {
        assertAll("Check Existence of Skill in the Inventory",
                () -> assertFalse(i.isSkillExist(s1)),
                () -> assertFalse(i.isSkillExist(s2))
        );
    }

    @Test
    void isEmpty() {
        assertTrue(i.isEmpty());
    }

    @Test
    void isFull() {
        assertFalse(i.isFull());
    }

    @Test
    void isEngimonBagEmpty() {
        assertTrue(i.isEngimonBagEmpty());
    }

    @Test
    void isSkillBagEmpty() {
        assertTrue(i.isSkillBagEmpty());
    }

    @Test
    void engimonBagSize() {
        assertEquals(0, i.engimonBagSize());
    }

    @Test
    void addEngimon() {
        Engimon e4 = new Engimon();
        Dragon e5 = new Dragon();
        Squirtle e6 = new Squirtle();
        assertAll("Add Engimon to The Inventory",
                () -> assertTrue(i.addEngimon(e4)),
                () -> assertTrue(i.addEngimon(e5)),
                () -> assertTrue(i.addEngimon(e6)),
                () -> assertTrue(i.addEngimon(e1)),
                () -> assertTrue(i.addEngimon(e2)),
                () -> assertFalse(i.addEngimon(e3))
        );
    }

    @Test
    void addSkill() {
        Waveform s4 = new Waveform();
        Sunstrike s5 = new Sunstrike();
        Torrent s6 = new Torrent();
        assertAll("Add Engimon to The Inventory",
                () -> assertTrue(i.addSkill(s4)),
                () -> assertTrue(i.addSkill(s5)),
                () -> assertTrue(i.addSkill(s6)),
                () -> assertTrue(i.addSkill(s1)),
                () -> assertTrue(i.addSkill(s2)),
                () -> assertFalse(i.addSkill(s3))
        );
    }

    @Test
    void removeEngimon() {
        List<Engimon> l = new ArrayList<>();
        i.addEngimon(e2);
        i.addEngimon(e3);
        l.add(e2);
        l.add(e3);
        assertEquals(l, i.listEngimon());
        i.removeEngimon(e3);
        assertNotEquals(l, i.listEngimon());
        l.removeAll(l);
        i.removeEngimon(1);
        assertEquals(l, i.listEngimon());
    }

    @Test
    void removeSkill() {
        i.addSkill(s1);
        i.addSkill(s2);
        assertTrue(i.isSkillExist(s1));
        assertTrue(i.isSkillExist(s2));
        i.removeSkill(2);
        assertTrue(i.isSkillExist(s1));
        assertFalse(i.isSkillExist(s2));
    }

//    @Test
//    void printItem() {
//    }
//
//    @Test
//    void printAllSkillInfo() {
//    }
//
//    @Test
//    void printAllEngimonInfo() {
//    }

    @Test
    void listEngimon() {
        List<Engimon> l = new ArrayList<>();
        assertEquals(l, i.listEngimon());
        i.addEngimon(e2);
        l.add(e2);
        assertEquals(l, i.listEngimon());
    }

    @Test
    void listSkill() {
        List<Skill> l = new ArrayList<>();
        assertEquals(l, i.listSkill());
        i.addSkill(s2);
        l.add(s2);
        assertEquals(l, i.listSkill());
    }

    @Test
    void getHighestLevel() {
        Inventory i2 = new Inventory();

        e2.setLevel(3);
        i.addEngimon(e2);
        i.addEngimon(e1);
        assertEquals(3, i.getHighestLevel());
        assertEquals(1, i2.getHighestLevel());
    }
}
