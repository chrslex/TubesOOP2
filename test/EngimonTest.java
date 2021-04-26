import oopokemon.skill.*;
import oopokemon.species.*;
import org.junit.jupiter.api.*;

import static oopokemon.element.ElementType.*;
import static org.junit.jupiter.api.Assertions.*;

class EngimonTest {
    private Engimon e1;
    private Articuno e2;
    private Inferail e3;
    private Engimon e4;
    private Seismotoad e5;
    private Skill s1;
    private Sunstrike s2;
    private Nimbus s3;
    private StaticStorm s4;
    private SplinterBlast s5;
    private IceVortex s6;

    @BeforeEach
    void setUp() {
        e1 = new Engimon();
        e2 = new Articuno("Artik");
        e3 = new Inferail("Inferred");
        e4 = new Engimon(e1);
        e5 = new Seismotoad("Toad");
        s1 = new Skill();
        s2 = new Sunstrike();
        s3 = new Nimbus();
        s4 = new StaticStorm();
        s5 = new SplinterBlast();
        s6 = new IceVortex();
    }

    @Test
    void isContainSkill() {
        assertAll("Engimon contains Skill",
                () -> assertTrue(e1.isContainSkill(s1)),
                () -> assertTrue(e4.isContainSkill(s1)),
                () -> assertTrue(e2.isContainSkill(s6)),
                () -> assertTrue(e3.isContainSkill(s2)),
                () -> assertTrue(e3.isContainSkill(s4)),
                () -> assertTrue(e2.isContainSkill(s1)),
                () -> assertFalse(e4.isContainSkill(s3)),
                () -> assertFalse(e1.isContainSkill(s2)),
                () -> assertFalse(e2.isContainSkill(s2)),
                () -> assertFalse(e2.isContainSkill(s5)),
                () -> assertFalse(e3.isContainSkill(s3))
        );
    }

    @Test
    void learnSkill() {
        assertAll("Engimon learns Skill",
                () -> assertTrue(e2.learnSkill(s5)),
                () -> assertTrue(e3.learnSkill(s3)),
                () -> assertFalse(e3.learnSkill(s2)),
                () -> assertFalse(e1.learnSkill(s2)),
                () -> assertFalse(e4.learnSkill(s3)),
                () -> assertFalse(e2.learnSkill(s2)),
                () -> assertFalse(e3.learnSkill(s2)),
                () -> assertFalse(e2.learnSkill(s6))
        );
    }

    @Test
    void getNamaSpecies() {
        assertAll("Get Engimon's Species name",
                () -> assertEquals("", e1.getNamaSpecies()),
                () -> assertEquals("Articuno", e2.getNamaSpecies()),
                () -> assertEquals("Inferail", e3.getNamaSpecies()),
                () -> assertEquals("", e4.getNamaSpecies())
        );
    }

    @Test
    void getName() {
        assertAll("Get Engimon's name",
                () -> assertEquals("", e1.getName()),
                () -> assertEquals("Artik", e2.getName()),
                () -> assertEquals("Inferred", e3.getName()),
                () -> assertEquals("", e4.getName())
        );
    }

    @Test
    void getLevel() {
        e2.setLevel(2);
        e2.addExp(100);
        e4.addExp(100);
        assertAll("Get Engimon's level",
                () -> assertEquals(1, e1.getLevel()),
                () -> assertEquals(3, e2.getLevel()),
                () -> assertEquals(1, e3.getLevel()),
                () -> assertEquals(2, e4.getLevel())
        );
    }

    @Test
    void getBaseLevel() {
        e1.setLevel(2);
        e1.addExp(100);
        e2.setLevel(3);
        e3.addExp(200);
        assertAll("Get Engimon's base level",
                () -> assertEquals(2, e1.getBaseLevel()),
                () -> assertEquals(3, e2.getBaseLevel()),
                () -> assertEquals(1, e3.getBaseLevel()),
                () -> assertEquals(1, e4.getBaseLevel())
        );
    }

    @Test
    void getLife() {
        e4.setLife(2);
        assertAll("Get Engimon's life",
                () -> assertEquals(3, e1.getLife()),
                () -> assertEquals(3, e2.getLife()),
                () -> assertEquals(3, e3.getLife()),
                () -> assertEquals(2, e4.getLife())
        );
    }

    @Test
    void getExp() {
        assertTrue(e1.addExp(100));
        assertFalse(e2.addExp(2001));
        assertTrue(e3.addExp(1000));
        assertTrue(e4.addExp(500));
        assertAll("Get Engimon's Exp",
                () -> assertEquals(100, e1.getExp()),
                () -> assertEquals(2001, e2.getExp()),
                () -> assertEquals(1000, e3.getExp()),
                () -> assertEquals(500, e4.getExp())
        );
    }

    @Test
    void getFirstElement() {
        assertAll("Get Engimon's First Element",
                () -> assertEquals(None, e1.getFirstElement()),
                () -> assertEquals(Ice, e2.getFirstElement()),
                () -> assertEquals(Fire, e3.getFirstElement()),
                () -> assertEquals(None, e4.getFirstElement())
        );
    }

    @Test
    void getSecondElement() {
        assertAll("Get Engimon's Second Element",
                () -> assertEquals(None, e1.getSecondElement()),
                () -> assertEquals(None, e2.getSecondElement()),
                () -> assertEquals(Electric, e3.getSecondElement()),
                () -> assertEquals(None, e4.getSecondElement())
        );
    }

    @Test
    void sumSkillPower() {
        assertTrue(e2.learnSkill(s5));
        assertAll("Get Engimon's Sum Skill Power",
                () -> assertEquals(0, e1.sumSkillPower()),
                () -> assertEquals(29, e2.sumSkillPower()),
                () -> assertEquals(29, e3.sumSkillPower()),
                () -> assertEquals(0, e4.sumSkillPower())
        );
    }

    @Test
    void getTypeInt() {
        String e1Name = e1.getNamaSpecies();
        String e2Name = e2.getNamaSpecies();
        String e3Name = e3.getNamaSpecies();
        String e4Name = e4.getNamaSpecies();
        assertAll("Get Engimon Type in Integer",
                () -> assertEquals(9, Engimon.getTypeInt(e1Name)),
                () -> assertEquals(4, Engimon.getTypeInt(e2Name)),
                () -> assertEquals(5, Engimon.getTypeInt(e3Name)),
                () -> assertEquals(9, Engimon.getTypeInt(e4Name))
        );
    }

    @Test
    void maxFloat() {
        assertAll("Get Maximum Number Between Two Float Numbers",
                () -> assertEquals(6.1f, Engimon.maxFloat(1.9f, 6.1f)),
                () -> assertEquals(1690.419f, Engimon.maxFloat(1582.219f, 1690.419f)),
                () -> assertEquals(282.482f, Engimon.maxFloat(282.481f, 282.482f)),
                () -> assertEquals(102.582f, Engimon.maxFloat(102.582f, 102.582f))
        );
    }

    @Test
    void maxElAdv() {
        assertAll("Get Maximum Element Advantage Between Two Engimon(s)",
                () -> assertEquals(-5, Engimon.maxElAdv(e1, e2)),
                () -> assertEquals(-5, Engimon.maxElAdv(e2, e1)),
                () -> assertEquals(2, Engimon.maxElAdv(e3, e2)),
                () -> assertEquals(2, Engimon.maxElAdv(e3, e5)),
                () -> assertEquals(2, Engimon.maxElAdv(e2, e5))
        );
    }

    @Test
    void testToString() {
        String e1Desc = "Engimon || Nama :  || Spesies :  || Level : 1 || Elemen 1 : None || Elemen 2 : None";
        String e2Desc = "Engimon || Nama : Artik || Spesies : Articuno || Level : 1 || Elemen 1 : Ice || Elemen 2 : None";
        String e3Desc = "Engimon || Nama : Inferred || Spesies : Inferail || Level : 1 || Elemen 1 : Fire || Elemen 2 : Electric";
        String e4Desc = "Engimon || Nama :  || Spesies :  || Level : 1 || Elemen 1 : None || Elemen 2 : None";
        String e5Desc = "Engimon || Nama : Toad || Spesies : Seismotoad || Level : 1 || Elemen 1 : Water || Elemen 2 : Ground";
        assertAll("Engimon's Description",
                () -> assertEquals(e1Desc, e1.toString()),
                () -> assertEquals(e2Desc, e2.toString()),
                () -> assertEquals(e3Desc, e3.toString()),
                () -> assertEquals(e4Desc, e4.toString()),
                () -> assertEquals(e5Desc, e5.toString())
        );
    }

    @Test
    void compareTo() {
        assertAll("Compare First Engimon to The Second Engimon",
                () -> assertEquals(-5, e1.compareTo(e2)),
                () -> assertEquals(-3, e2.compareTo(e3)),
                () -> assertEquals(17, e3.compareTo(e5)),
                () -> assertEquals(0, e4.compareTo(e1)),
                () -> assertEquals(-14, e5.compareTo(e2))
        );
    }

    @Test
    void getFirstLine() {
        assertAll("Get Engimon's Name",
                () -> assertEquals("Nama : ", e1.getFirstLine()),
                () -> assertEquals("Nama : Artik", e2.getFirstLine()),
                () -> assertEquals("Nama : Inferred", e3.getFirstLine()),
                () -> assertEquals("Nama : ", e4.getFirstLine())
        );
    }

    @Test
    void getSecondLine() {
        String e1SpEl = "Spesies : \nElement 1 : None";
        String e2SpEl = "Spesies : Articuno\nElement 1 : Ice";
        String e3SpEl = "Spesies : Inferail\nElement 1 : Fire\nElement 2 : Electric";
        String e4SpEl = "Spesies : \nElement 1 : None";
        assertAll("Get Engimon's Nama Spesies & Element(s)",
                () -> assertEquals(e1SpEl, e1.getSecondLine()),
                () -> assertEquals(e2SpEl, e2.getSecondLine()),
                () -> assertEquals(e3SpEl, e3.getSecondLine()),
                () -> assertEquals(e4SpEl, e4.getSecondLine())
        );
    }

    @Test
    void getThirdLine() {
        assertTrue(e1.addExp(200));
        assertTrue(e2.addExp(300));
        assertTrue(e3.addExp(1200));
        assertTrue(e4.addExp(1700));
        assertAll("Get Engimon's Exp",
                () -> assertEquals("Exp : 200/2000\nLvl : 3\nLife : 3", e1.getThirdLine()),
                () -> assertEquals("Exp : 300/2000\nLvl : 4\nLife : 3", e2.getThirdLine()),
                () -> assertEquals("Exp : 1200/2000\nLvl : 13\nLife : 3", e3.getThirdLine()),
                () -> assertEquals("Exp : 1700/2000\nLvl : 18\nLife : 3", e4.getThirdLine())
        );
    }

    @Test
    void getFourthLine() {
        String skill2 = "Skill || Nama : Ice Vortex || Elemen : Ice || Base Power : 13 || Mastery Level : 1\n";
        String skill3 = "Skill || Nama : Sunstrike || Elemen : Fire || Base Power : 15 || Mastery Level : 1\n"+
                "Skill || Nama : Static Storm || Elemen : Electric || Base Power : 14 || Mastery Level : 1\n";
        assertAll("Get Engimon's Skill(s)",
                () -> assertEquals("", e1.getFourthLine()),
                () -> assertEquals(skill2, e2.getFourthLine()),
                () -> assertEquals(skill3, e3.getFourthLine()),
                () -> assertEquals("", e4.getFourthLine())
        );
    }

    @Test
    void getFifthLine() {
        Engimon e6 = new Engimon("Saha", e2, e3);
        String parentDesc = "Parent | Nama: Artik Spesies: Articuno\nParent | Nama: Inferred Spesies: Inferail\n";
        assertAll("Get Engimon's Parents",
                () -> assertEquals("", e1.getFifthLine()),
                () -> assertEquals("", e2.getFifthLine()),
                () -> assertEquals(parentDesc, e6.getFifthLine())
        );
    }
}
