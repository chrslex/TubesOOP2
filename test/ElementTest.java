import oopokemon.element.Element;
import org.junit.jupiter.api.*;

import static oopokemon.element.ElementType.*;
import static org.junit.jupiter.api.Assertions.*;

class ElementTest {
    private static Element e1;
    private static Element e2;
    private static Element e3;

    @BeforeEach
    void setUp() {
        e1 = new Element();
        e2 = new Element(Fire);
        e3 = new Element(Ice);
    }

    @Test
    @DisplayName("Get Element Type of each Element")
    void getElementType() {
        assertAll("Element Type",
            () -> assertEquals(None, e1.getElementType(), "Tipe elemen dari e1 adalah None"),
            () -> assertEquals(Fire, e2.getElementType(), "Tipe elemen dari e2 adalah Fire")
        );
    }

    @Test
    @DisplayName("Get the advantage of ElementType of first element compared to second element")
    void getElementAdv() {
        assertAll("Element Advantage",
                () -> assertEquals(-5, Element.getElementAdv(e1.getElementType(), e2.getElementType()), "Tipe elemen None memiliki advantage sebesar -5 dari tipe elemen Fire"),
                () -> assertEquals(2, Element.getElementAdv(e2.getElementType(), e3.getElementType()), "Tipe elemen Fire memiliki advantage sebesar 2 dari tipe elemen Ice"),
                () -> assertEquals(-5, Element.getElementAdv(e3.getElementType(), e1.getElementType()), "Tipe elemen Ice memiliki advantage sebesar -5 dari tipe elemen None")

        );
    }

    @Test
    @DisplayName("Change ElementType of an Element")
    void setElementType() {
        e1.setElementType(Water);
        e2.setElementType(None);
        e3.setElementType(Ground);
        assertAll("Check Element Type",
                () -> assertEquals(Water, e1.getElementType()),
                () -> assertEquals(None, e2.getElementType()),
                () -> assertEquals(Ground, e3.getElementType())
        );
    }

    @Test
    @DisplayName("Compare ElementType of two Element(s)")
    void testEquals() {
        Element e4 = new Element(Fire);
        assertTrue(e2.equals(e4));
        assertFalse(e1.equals(e2));
    }

    @Test
    @DisplayName("Change ElementType of each Element to String")
    void testToString() {
        assertAll("Element Type to String",
                () -> assertEquals("None", e1.toString()),
                () -> assertEquals("Fire", e2.toString()),
                () -> assertEquals("Ice", e3.toString())
        );
    }

    @Test
    @DisplayName("Compare ElementType of two Element(s)")
    void compareTo() {
        assertAll("Compare Two Element(s)",
                () -> assertEquals(8, e1.compareTo(e2), "Perbandingan leksikograf dari tipe elemen e1 dengan e2 adalah 8"),
                () -> assertEquals(-3, e2.compareTo(e3), "Perbandingan leksikograf dari tipe elemen e2 dengan e3 adalah 8"),
                () -> assertEquals(-5, e3.compareTo(e1), "Perbandingan leksikograf dari tipe elemen e3 dengan e1 adalah 8")
        );
    }
}
