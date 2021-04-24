package oopokemon.occupier;

import oopokemon.map.CellType;
import oopokemon.map.Map;
import oopokemon.map.Position;
import oopokemon.species.Engimon;
import oopokemon.exception.NotInitializedException;
import oopokemon.species.*;

import java.util.Random;

import static oopokemon.map.CellType.*;

public class Enemy extends Occupier{
    
    private Engimon engimon;
    private final CellType cellType1;
    private final CellType cellType2;


    public Enemy(Map map, int angka, int level) throws NotInitializedException {
        super(map);

        Random rand = new Random();

        int hashCellType;
        switch (angka)
        {
            case 1:
                engimon = new Squirtle();
                break;
            case 2:
                engimon = new Raichu();
                break;
            case 3:
                engimon = new Excadrill();
                break;
            case 4:
                engimon = new Articuno();
                break;
            case 5:
                engimon = new Inferail();
                break;
            case 6:
                engimon = new Kyogre();
                break;
            case 7:
                engimon = new Seismotoad();
                break;
            case 8:
            default:
                engimon = new Dragon();
                break;

        }
        switch (engimon.getFirstElement())
        {
            case Water:
                cellType1 = Sea_Cell;
                break;
            case Fire:
                cellType1 = Mountain_Cell;
                break;
            case Ice:
                cellType1 = Tundra_Cell;
                break;
            default:
                cellType1 = Grassland_Cell;
                break;
        }
        switch (engimon.getSecondElement())
        {
            case None:
                cellType2 = cellType1;
                break;
            case Water:
                cellType2 = Sea_Cell;
                break;
            case Fire:
                cellType2 = Mountain_Cell;
                break;
            case Ice:
                cellType2 = Tundra_Cell;
                break;
            default:
                cellType2 = Grassland_Cell;
                break;
        }

        int posisirand, x, y;
        do {
            posisirand = rand.nextInt(Position.MAX_X * Position.MAX_Y);
            x = posisirand % Position.MAX_X;
            y = posisirand / Position.MAX_X;
        } while (!setPos(x, y));


        engimon.setLevel(level);
        sprite.setCenterImage(engimon.getImage());
	    sprite.setBottomRightImage(engimon.getElementImage());

//        System.out.println(position.x + ", " + position.y);
    }

    public boolean setPos(int x, int y) {
        if (!Position.isValidCoordinate(x,y)) return false;
        CellType mapCellType = map.getCellAtPosition(new Position(x, y)).getCellType();
        if (mapCellType == null) return false;
        if (this.cellType1 != mapCellType && this.cellType2 != mapCellType) return false;
        return setPositionOcc(x, y);
    }

    public void setEngimon(Engimon engimon) {
        this.engimon = engimon;
    }

    public Engimon getEngimon() {
        return engimon;
    }

    private CellType getFirstCellType(){
        return cellType1;
    }

    private CellType getSecondCellType(){
        return cellType2;

    }

    public int getExp() {
        return engimon.getExp();
    }

    public void setExp(int exp) {
        engimon.addExp(exp);
    }


    public boolean move(int rand){
        int x = this.position.x;
        int y = this.position.y;
        switch (rand){
            case 0:
                return setPos(x,--y);
            case 1:
                return setPos(--x,y);
            case 2:
                return setPos(x,++y);
            case 3:
                return setPos(++x,y);
            default:
                return false;
        }
    }
}
