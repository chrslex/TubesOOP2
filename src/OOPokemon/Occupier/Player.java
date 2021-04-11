package OOPokemon.Occupier;

import OOPokemon.Map.Map;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends Occupier {

    public Player(Pane node, Map map) {
        super(0,0, OccupierType.Player_Type, node, map);
        this.sprite.setCenterImage(new Image("assets/player.png"));
    }

    public Player(int x, int y, Pane node, Map map) {
        super(x,y, OccupierType.Player_Type, node, map);
        this.sprite.setCenterImage(new Image("assets/player.png"));
    }


}
