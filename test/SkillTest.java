import oopokemon.skill.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {
    private Skill s1;
    private Cataclysm s2;
    private Skill s3;
    private Nimbus s4;
    private SplinterBlast s5;
    private String s6;

    @BeforeEach
    void setUp() {
        s1 = new Skill();
        s2 = new Cataclysm();
        s3 = new Skill();
        s4 = new Nimbus();
        s5 = new SplinterBlast();
    }

    @Test
    void getSkillName() {
        assertAll("Get Skill Name",
                () -> assertEquals("None", s1.getSkillName()),
                () -> assertEquals("Cataclysm", s2.getSkillName()),
                () -> assertEquals("None", s3.getSkillName()),
                () -> assertEquals("Nimbus", s4.getSkillName()),
                () -> assertEquals("SplinterBlast", s5.getSkillName())
        );
    }

    @Test
    void getBasePower() {
        assertAll("Get Skill's Base Power",
                () -> assertEquals(0, s1.getBasePower()),
                () -> assertEquals(18, s2.getBasePower()),
                () -> assertEquals(0, s3.getBasePower()),
                () -> assertEquals(16, s4.getBasePower()),
                () -> assertEquals(16, s5.getBasePower())
        );
    }

    @Test
    void isSame() {
        assertAll("Equality Skill",
                () -> assertTrue(s1.isSame(s3)),
                () -> assertFalse(s2.isSame(s1)),
                () -> assertFalse(s4.isSame(s5))
        );
    }

    @Test
    void isNotSame() {
        assertAll("Inequality Skill",
                () -> assertTrue(s2.isNotSame(s1)),
                () -> assertTrue(s4.isNotSame(s5)),
                () -> assertFalse(s1.isNotSame(s3))
        );
    }

    @Test
    void isHigher() {
        assertAll("Mastery Level of a Skill is higher",
                () -> assertTrue(s2.isHigher(s1)),
                () -> assertTrue(s4.isHigher(s3)),
                () -> assertFalse(s2.isHigher(s5)),
                () -> assertFalse(s5.isHigher(s4))
        );
    }

    @Test
    void isSameOrHigher() {
        assertAll("Mastery Level of a Skill is same or higher",
                () -> assertTrue(s2.isSameOrHigher(s1)),
                () -> assertTrue(s5.isSameOrHigher(s4)),
                () -> assertFalse(s1.isSameOrHigher(s5)),
                () -> assertFalse(s3.isSameOrHigher(s2))
        );
    }

    @Test
    void isLower() {
        assertAll("Mastery Level of a Skill is lower",
                () -> assertTrue(s1.isLower(s2)),
                () -> assertTrue(s3.isLower(s4)),
                () -> assertFalse(s4.isLower(s5)),
                () -> assertFalse(s1.isLower(s3))
        );
    }

    @Test
    void isSameOrLower() {
        assertAll("Mastery Level of a Skill is same or lower",
                () -> assertTrue(s1.isSameOrLower(s2)),
                () -> assertTrue(s3.isSameOrLower(s4)),
                () -> assertTrue(s4.isSameOrLower(s5)),
                () -> assertTrue(s1.isSameOrLower(s3)),
                () -> assertFalse(s4.isSameOrLower(s1))
        );
    }

    @Test
    void testEquals() {
        assertAll("Skill Equality",
                () -> assertEquals(s1, s3),
                () -> assertNotEquals(s2, s1),
                () -> assertNotEquals(s4, s5),
                () -> assertNotEquals(s3, s6)
        );
    }

    @Test
    void compareTo() {
        assertAll("Skill Compare To",
                () -> assertEquals(0, s1.compareTo(s3)),
                () -> assertEquals(-18, s2.compareTo(s1)),
                () -> assertEquals(0, s4.compareTo(s5)),
                () -> assertEquals(16, s3.compareTo(s5))
        );
    }

    @Test
    void testToString() {
        assertAll("Skill Description",
                () -> assertEquals("Skill || Nama : None || Elemen : None || Base Power : 0 || Mastery Level : 0", s1.toString()),
                () -> assertEquals("Skill || Nama : Cataclysm || Elemen : Fire || Base Power : 18 || Mastery Level : 1", s2.toString()),
                () -> assertEquals("Skill || Nama : None || Elemen : None || Base Power : 0 || Mastery Level : 0", s3.toString()),
                () -> assertEquals("Skill || Nama : Nimbus || Elemen : Electric || Base Power : 16 || Mastery Level : 1", s4.toString()),
                () -> assertEquals("Skill || Nama : SplinterBlast || Elemen : Ice || Base Power : 16 || Mastery Level : 1", s5.toString())
        );
    }

    @Test
    void testHashCode() {
        assertAll("Hash Skill",
                () -> assertEquals(8, s1.hashCode()),
                () -> assertEquals(13, s2.hashCode()),
                () -> assertEquals(8, s3.hashCode()),
                () -> assertEquals(14, s4.hashCode()),
                () -> assertEquals(16, s5.hashCode())
        );
    }

    @Test
    void increaseMasteryLevel() {
        s1.increaseMasteryLevel();
        for (int i = 0; i <= 3; i++) { // Ingin menaikkan mastery level dari 0 menjadi 4
            s3.increaseMasteryLevel();
        }
        s2.increaseMasteryLevel();
        s4.increaseMasteryLevel();
        assertAll("Increase Skill's Mastery Level",
                () -> assertTrue(s2.isSameOrHigher(s4)),
                () -> assertTrue(s3.isSameOrHigher(s2)),
                () -> assertFalse(s1.isSameOrHigher(s3)),
                () -> assertFalse(s4.isSameOrHigher(s3))
        );
    }

    @Test
    void getFirstLine() {
        assertAll("Get Skill's Name",
                () -> assertEquals("None", s1.getFirstLine()),
                () -> assertEquals("Cataclysm", s2.getFirstLine()),
                () -> assertEquals("None", s3.getFirstLine()),
                () -> assertEquals("Nimbus", s4.getFirstLine()),
                () -> assertEquals("SplinterBlast", s5.getFirstLine())
        );
    }

    @Test
    void getSecondLine() {
        assertAll("Get Skill's Type",
                () -> assertEquals("Elemen : None", s1.getSecondLine()),
                () -> assertEquals("Elemen : Fire", s2.getSecondLine()),
                () -> assertEquals("Elemen : None", s3.getSecondLine()),
                () -> assertEquals("Elemen : Electric", s4.getSecondLine()),
                () -> assertEquals("Elemen : Ice", s5.getSecondLine())
        );
    }

    @Test
    void getThirdLine() {
        assertAll("Get Skill's Base Power",
                () -> assertEquals("Base Power : 0", s1.getThirdLine()),
                () -> assertEquals("Base Power : 18", s2.getThirdLine()),
                () -> assertEquals("Base Power : 0", s3.getThirdLine()),
                () -> assertEquals("Base Power : 16", s4.getThirdLine()),
                () -> assertEquals("Base Power : 16", s5.getThirdLine())
        );
    }

    @Test
    void getFourthLine() {
        assertAll("Get Skill's Mastery Level",
                () -> assertEquals("Mastery Level : 0", s1.getFourthLine()),
                () -> assertEquals("Mastery Level : 1", s2.getFourthLine()),
                () -> assertEquals("Mastery Level : 0", s3.getFourthLine()),
                () -> assertEquals("Mastery Level : 1", s4.getFourthLine()),
                () -> assertEquals("Mastery Level : 1", s5.getFourthLine())
        );
    }

    @Test
    void getFifthLine() {
        assertEquals("", s1.getFifthLine());
    }
}
