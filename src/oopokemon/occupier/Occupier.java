package oopokemon.occupier;

import oopokemon.map.Cell;
import oopokemon.map.Map;
import oopokemon.map.Position;
import oopokemon.exception.NotInitializedException;
import oopokemon.misc.Renderable;
import oopokemon.misc.Sprite;

import javafx.scene.Node;

public abstract class Occupier implements Renderable {
    protected Sprite sprite;
    public OccupierType occupierType;
    public Position position;
    protected Map map;

    private static final float cellHeight = Cell.cellHeight;
    private static final float cellWidth = Cell.cellWidth;

    Occupier(Map map) throws NotInitializedException {
        if (map == null) throw new NotInitializedException();
        sprite = new Sprite();
        occupierType = OccupierType.Enemy_Type;
        position = new Position();
        this.map = map;
        setPositionOcc(0,0);
    }

    Occupier(int x, int y, OccupierType occupierType, Map map) throws NotInitializedException {
        if (map == null) throw new NotInitializedException();
        sprite = new Sprite();
        this.occupierType = occupierType;
        position = (Position.isValidCoordinate(x,y))? new Position(x,y) : new Position();
        this.map = map;
        setPositionOcc(position.x, position.y);
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

    public Map getMap(){
        return map;
    }


    @Override
    public Node getToRender() {
        return sprite;
    }
}
