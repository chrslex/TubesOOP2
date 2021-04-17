package OOPokemon.Occupier;

import OOPokemon.Map.CellType;
import OOPokemon.Map.Map;
import OOPokemon.Map.Position;
import OOPokemon.Species.Engimon;
import OOPokemon.exception.NotInitializedException;
import OOPokemon.Species.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static OOPokemon.Map.CellType.*;

public class Enemy extends Occupier{
    
    private Engimon engimon;

//    public enum EngimonType{
//        ARTICUNO(0),
//        DRAGON(1),
//        EXCADRILL(2),
//        INFERAIL(3),
//        KYOGRE(4),
//        RAICHU(5),
//        SEISMOTOAD(6),
//        SQUIRTLE(7);
//
//
//        private final int value;
//
//        EngimonType(int value) {
//            this.value = value;
//        }
//    }

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
        CellType[] cellType = new CellType[2];
        switch (engimon.getFirstElement())
        {
            case Water:
                cellType[0] = Sea_Cell;
                break;
            case Fire:
                cellType[0] = Mountain_Cell;
                break;
            case Ice:
                cellType[0] = Tundra_Cell;
                break;
            default:
                cellType[0] = Grassland_Cell;
                break;
        }
        switch (engimon.getSecondElement())
        {
            case None:
                cellType[1] = cellType[0];
                break;
            case Water:
                cellType[1] = Sea_Cell;
                break;
            case Fire:
                cellType[1] = Mountain_Cell;
                break;
            case Ice:
                cellType[1] = Tundra_Cell;
                break;
            default:
                cellType[1] = Grassland_Cell;
                break;
        }

        int posisirand, x, y;
        CellType mapCellType;
        do {
            posisirand = rand.nextInt(Position.MAX_X * Position.MAX_Y);
            x = posisirand % Position.MAX_X;
            y = posisirand / Position.MAX_X;
            mapCellType = map.getCellAtPosition(new Position(x, y)).getCellType();

        } while ((cellType[0] != mapCellType && cellType[1] != mapCellType) || !setPositionOcc(x, y));


        engimon.setLevel(level);
        sprite.setCenterImage(engimon.getImage());
	    sprite.setBottomRightImage(engimon.getElementImage());


        System.out.println(position.x + ", " + position.y);
    }

}
