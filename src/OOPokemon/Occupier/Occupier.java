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

    private static final float cellHeight = Cell.cellHeight;
    private static final float cellWidth = Cell.cellWidth;

    Occupier(Pane node) {
        sprite = new Sprite();
        occupierType = OccupierType.Enemy_Type;
        position = new Position();
        node.getChildren().add(sprite);
        updateImagePosition();
    }

    Occupier(int x, int y, OccupierType occupierType, Pane node) {
        sprite = new Sprite();
        this.occupierType = occupierType;
        position = (Position.isValidCoordinate(x,y))? new Position(x,y) : new Position();
        node.getChildren().add(sprite);
        updateImagePosition();
    }

    protected void updateImagePosition() {
        sprite.setTranslateX(position.x * cellWidth);
        sprite.setTranslateY(position.y * cellHeight);
    }


    protected boolean setPositionOcc(int x, int y, Map map)
    {
        if (Position.isValidCoordinate(x,y))
        {
            Position posisiBaru = new Position(x,y);
            if (map.getCellAtPosition(posisiBaru).occupier != null)
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
