package OOPokemon.Occupier;

import OOPokemon.Map.Cell;
import OOPokemon.Map.Map;
import OOPokemon.Map.Position;
import OOPokemon.misc.Sprite;

import javafx.scene.layout.Pane;

public abstract class Occupier {
    protected Sprite sprite;
    public OccupierType occupierType;
    public Position position;
    protected Map map;

    private static final float cellHeight = Cell.cellHeight;
    private static final float cellWidth = Cell.cellWidth;

    Occupier(Pane node, Map map) {
        sprite = new Sprite();
        occupierType = OccupierType.Enemy_Type;
        position = new Position();
        this.map = map;
        node.getChildren().add(sprite);
        setPositionOcc(0,0);
        updateImagePosition();
    }

    Occupier(int x, int y, OccupierType occupierType, Pane node, Map map) {
        sprite = new Sprite();
        this.occupierType = occupierType;
        position = (Position.isValidCoordinate(x,y))? new Position(x,y) : new Position();
        this.map = map;
        node.getChildren().add(sprite);
        setPositionOcc(position.x, position.y);
        updateImagePosition();
    }

    protected void updateImagePosition() {
        sprite.setTranslateX(position.x * cellWidth);
        sprite.setTranslateY(position.y * cellHeight);
    }


    public boolean setPositionOcc(int x, int y)
    {
        if (Position.isValidCoordinate(x,y))
        {
            Position posisiBaru = new Position(x,y);
            if (map.getCellAtPosition(posisiBaru).occupier == null)
            {
                map.removeOccupierAtPosition(this.position);
                position = posisiBaru;
                map.addOccupier(this);
                updateImagePosition();
                return true;
            }
        }
        return false;
    }

}
