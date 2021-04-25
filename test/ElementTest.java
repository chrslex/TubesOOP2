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
        assertEquals(-5, Element.getElementAdv(e1.getElementType(), e2.getElementType()), "Tipe elemen None memiliki advantage sebesar -5 dari tipe elemen Fire");
    }

    @Test
    @DisplayName("Change ElementType of an Element")
    void setElementType() {
        e3.setElementType(Ground);
        assertEquals(Ground, e3.getElementType());
    }

    @Test
    @DisplayName("Compare ElementType of two Element(s)")
    void testEquals() {
        assertFalse(e1.equals(e2));
    }

    @Test
    @DisplayName("Change ElementType of each Element to String")
    void testToString() {
        assertAll("Element Type to String",
                () -> assertEquals("None", e1.toString()),
                () -> assertEquals("Fire", e2.toString())
        );
    }

    @Test
    @DisplayName("Compare ElementType of two Element(s)")
    void compareTo() {
        assertEquals(8, e1.compareTo(e2), "Perbandingan leksikograf dari tipe elemen e1 dengan e2 adalah 8");
    }
}
