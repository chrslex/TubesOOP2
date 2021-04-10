package OOPokemon.Occupier;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends Occupier {

    public Player(Pane node) {
        super(0,0, OccupierType.Player_Type, node);
        this.sprite.setCenterImage(new Image("assets/player.png"));
    }

    public Player(int x, int y, Pane node) {
        super(x,y, OccupierType.Player_Type, node);
        this.sprite.setCenterImage(new Image("assets/player.png"));
    }


}
