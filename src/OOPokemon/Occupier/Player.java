package OOPokemon.Occupier;

import OOPokemon.Map.Map;
import OOPokemon.exception.NotInitializedException;
import OOPokemon.misc.MusicPlayer;
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

    @Override
    public boolean setPositionOcc(int x, int y) {
        boolean ret =  super.setPositionOcc(x, y);

        if (ret) {
            String namaFile = map.getCellAtPosition(this.position).getCellType().toString();
            MusicPlayer musicPlayer = new MusicPlayer(namaFile, MusicPlayer.MusicType.SFX,false);
            musicPlayer.start();
        }

        return ret;
    }
}
