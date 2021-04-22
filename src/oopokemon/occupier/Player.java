package oopokemon.occupier;

import javafx.scene.image.ImageView;
import oopokemon.inventory.Inventory;
import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;
import oopokemon.map.Position;
import oopokemon.misc.MusicPlayer;
import javafx.scene.image.Image;
import oopokemon.species.Dragon;
import oopokemon.species.Engimon;

public class Player extends Occupier {

    private final Inventory inventory = new Inventory();
    private final ActiveEngimon activeEngimon;

    public ImageView healthbar = new ImageView(new Image("assets/life3.png"));

    public Player(Map map) throws NotInitializedException {
        super(0,0, OccupierType.Player_Type, map);
        init();
        activeEngimon = new ActiveEngimon(map, new Dragon());
//        activeEngimon.setEngimon(new Dragon());
    }

    public Player(int x, int y, Map map) throws NotInitializedException {
        super(x,y, OccupierType.Player_Type, map);
        init();
        activeEngimon = new ActiveEngimon(map);
        setActiveEngimon(new Dragon());
        activeEngimon.setPositionOcc(x-1, y);
//        setActiveEngimon(null);
//        activeEngimon.sprite.setCenterImage(new Image("assets/player.png"));
//        new Player(map);
    }

    private void init() {
        sprite.setCenterImage(new Image("assets/player.png"));
        healthbar.setFitHeight(70);
        healthbar.setX(30);
        healthbar.setY(30);
        healthbar.setPreserveRatio(true);
    }

    public int getLevel(){
        return activeEngimon.getLevel();
    }

    public int getLife(){
        return activeEngimon.getLife();
    }

    public int getHighestLevel(){
        int lvla = activeEngimon.getLevel();
        int lvlb = inventory.getHighestLevel();
        return Math.max(lvla, lvlb);
    }

    public void setActiveEngimon(Engimon engimon){
        activeEngimon.setEngimon(engimon);
        int life = getLife();
        healthbar.setImage(new Image("assets/life" + life + ".png"));
    }


    @Override
    public boolean setPositionOcc(int x, int y) {
//        boolean ret =  super.setPositionOcc(x, y);

        if (Position.isValidCoordinate(x,y))
        {
            Position posisiBaru = new Position(x,y);
            if (map.getCellAtPosition(posisiBaru).occupier == null || map.getCellAtPosition(posisiBaru).occupier.occupierType == OccupierType.Pet_Type)
            {
                map.removeOccupierAtPosition(this.position);
                if (activeEngimon != null) {
//                    System.out.println(activeEngimon.getEngimon());
                    activeEngimon.setPositionOcc(this.position.x, this.position.y);
                }
                position = posisiBaru;
                map.addOccupier(this);
                updateImagePosition();
                String namaFile = map.getCellAtPosition(this.position).getCellType().getClip();
                MusicPlayer musicPlayer = new MusicPlayer(namaFile, MusicPlayer.MusicType.SFX,false);
                musicPlayer.run();
                return true;
            }
        }
        return false;
    }

}
