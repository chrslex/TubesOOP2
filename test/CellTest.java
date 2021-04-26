import oopokemon.map.Cell;
import oopokemon.occupier.Occupier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static oopokemon.map.CellType.*;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell c1;
    private Cell c2;
    private Cell c3;
    private Occupier o1;
    private Occupier o2;

    @BeforeEach
    void setUp() {
        c1 = new Cell();
        c2 = new Cell(16, 23, Grassland_Cell);
        c3 = new Cell(6, 15, Tundra_Cell);
    }

    @Test
    void getCellType() {
        assertEquals(Grassland_Cell, c1.getCellType());
        assertEquals(Grassland_Cell, c2.getCellType());
        assertEquals(Tundra_Cell, c3.getCellType());
    }

    @Test
    void setOccupier() {
        c1.setOccupier(o2);
        c3.setOccupier(o1);
        assertEquals(o2, c1.getOccupier());
        assertNull(c2.getOccupier());
        assertEquals(o1, c3.getOccupier());
    }
}
