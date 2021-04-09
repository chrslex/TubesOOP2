package OOPokemon.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public Position position;
    public CellType cellType;
    public Occupier occupier;

    // Configuration parameter
    public static int cellWidth = 20;
    public static int cellHeight = 40;


    Cell() {
        super(0,0, cellWidth, cellHeight);
        position = new Position();
        cellType = CellType.Grassland_Cell;
        setFill(Color.LAWNGREEN);
        occupier = null;
    }

    Cell(int x, int y, CellType cellType) {
        super(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
        position = new Position(x,y);
        this.cellType = cellType;
        switch (cellType){
            case Grassland_Cell:
                setFill(Color.LAWNGREEN);
                break;
            case Sea_Cell:
                setFill(Color.ROYALBLUE);
        }
        occupier = null;
    }

    public void setOccupier(Occupier occupier) {
        this.occupier = occupier;
    }
}
