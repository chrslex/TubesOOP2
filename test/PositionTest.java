import oopokemon.map.Position;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Position p1;
    private Position p2;

    @BeforeEach
    void setUp() {
        p1 = new Position();
        p2 = new Position(27,14);
    }

    @Test
    void setPosition() {
        assertFalse(p1.setPosition(31, 28));
        assertFalse(p2.setPosition(-2, -13));
        assertTrue(p1.setPosition(21, 16));
        assertTrue(p2.setPosition(2, 8));
    }

    @Test
    void isValidCoordinate() {
        assertTrue(Position.isValidCoordinate(0, 0));
        assertTrue(Position.isValidCoordinate(20, 10));
        assertTrue(Position.isValidCoordinate(29, 19));
        assertFalse(Position.isValidCoordinate(30, 20));
        assertFalse(Position.isValidCoordinate(-1, -1));
    }
}
