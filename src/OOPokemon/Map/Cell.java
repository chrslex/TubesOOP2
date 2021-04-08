package OOPokemon.Map;

public class Cell {
    public Position position;
    public CellType cellType;
    public Occupier occupier;

    Cell() {
        position = new Position();
        cellType = CellType.Grassland_Cell;
        occupier = null;
    }

    Cell(int x, int y, CellType cellType) {
        position = new Position(x,y);
        this.cellType = cellType;
        occupier = null;
    }

    public void setOccupier(Occupier occupier) {
        this.occupier = occupier;
    }
}
