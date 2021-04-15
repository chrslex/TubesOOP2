package OOPokemon.Occupier;

import OOPokemon.Map.CellType;
import OOPokemon.Map.Map;
import OOPokemon.Map.Position;
import OOPokemon.Species.Engimon;
import OOPokemon.exception.NotInitializedException;
import OOPokemon.Species.*;

import java.util.Random;

import static OOPokemon.Map.CellType.*;

public class Enemy extends Occupier{
    
    private Engimon engimon;
    private CellType cellType;

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
            case Ice:
                cellType = Sea_Cell;
                hashCellType = 1;
                break;
            default:
                cellType = Grassland_Cell;
                hashCellType = 10;
                break;
        }
        switch (engimon.getSecondElement())
        {
            case None:
                break;
            case Water:
            case Ice:
                hashCellType += 1;
                break;
            default:
                hashCellType += 10;
                break;
        }
        if (hashCellType == 11) cellType = Rancu;

        int posisirand ;

        if (cellType == Rancu){
            do {
                posisirand = rand.nextInt(Position.MAX_X * Position.MAX_Y);

            } while (!setPositionOcc(posisirand % Position.MAX_X, posisirand / Position.MAX_X)) ;
        }
	    else {
	        int x;
	        int y;
            do {
                posisirand = rand.nextInt(Position.MAX_X * Position.MAX_Y);
                x = posisirand % Position.MAX_X;
                y = posisirand / Position.MAX_X;

            } while (cellType != map.getCellAtPosition(new Position(x, y)).getCellType() || !setPositionOcc(x, y));
        }

        engimon.setLevel(level);
        sprite.setCenterImage(engimon.getImage());
	    sprite.setBottomRightImage(engimon.getElementImage());


        System.out.println(position.x + ", " + position.y);
    }

}
