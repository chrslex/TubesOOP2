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
    void getElementType() {
        assertAll("Element Type",
            () -> assertEquals(None, e1.getElementType()),
            () -> assertEquals(Fire, e2.getElementType())
        );
    }

    @Test
    void getElementAdv() {
        assertAll("Element Advantage",
                () -> assertEquals(-5, Element.getElementAdv(e1.getElementType(), e2.getElementType())),
                () -> assertEquals(2, Element.getElementAdv(e2.getElementType(), e3.getElementType())),
                () -> assertEquals(-5, Element.getElementAdv(e3.getElementType(), e1.getElementType()))

        );
    }

    @Test
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
    void testEquals() {
        Element e4 = new Element(Fire);
        assertEquals(e2, e4);
        assertNotEquals(e1, e2);
    }

    @Test
    void testToString() {
        assertAll("Element Type to String",
                () -> assertEquals("None", e1.toString()),
                () -> assertEquals("Fire", e2.toString()),
                () -> assertEquals("Ice", e3.toString())
        );
    }

    @Test
    void compareTo() {
        assertAll("Compare Two Element(s)",
                () -> assertEquals(8, e1.compareTo(e2)),
                () -> assertEquals(-3, e2.compareTo(e3)),
                () -> assertEquals(-5, e3.compareTo(e1))
        );
    }
}
