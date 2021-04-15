package OOPokemon.Occupier;

import OOPokemon.Map.Map;
import OOPokemon.exception.NotInitializedException;
import javafx.scene.image.Image;

public class Player extends Occupier {

    public Player(Map map) throws NotInitializedException {
        super(0,0, OccupierType.Player_Type, map);
        this.sprite.setCenterImage(new Image("assets/player.png"));
    }

    public Player(int x, int y, Map map) throws NotInitializedException {
        super(x,y, OccupierType.Player_Type, map);
        this.sprite.setCenterImage(new Image("assets/player.png"));
    }



}
