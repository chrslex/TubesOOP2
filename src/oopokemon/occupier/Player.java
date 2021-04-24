package oopokemon.occupier;

import javafx.scene.image.ImageView;
import oopokemon.inventory.Bag;
import oopokemon.inventory.Inventory;
import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;
import oopokemon.map.Position;
import oopokemon.misc.MusicPlayer;
import javafx.scene.image.Image;
import oopokemon.species.Dragon;
import oopokemon.species.Engimon;

public class Player extends Occupier {

    private final Inventory inventory;
    private final ActiveEngimon activeEngimon;

    public ImageView healthbar = new ImageView(new Image("assets/life3.png"));

    public Player(Map map) throws NotInitializedException {
        super(0,0, OccupierType.Player_Type, map);
        init();
        activeEngimon = new ActiveEngimon(map, new Dragon());
        inventory = new Inventory();
    }

    public Player(int x, int y, Map map) throws NotInitializedException {
        super(x,y, OccupierType.Player_Type, map);
        init();
        activeEngimon = new ActiveEngimon(map);
        setActiveEngimon(new Dragon());
        activeEngimon.setPositionOcc(x-1, y);
        inventory = new Inventory();
    }

    public Player(int x, int y, ActiveEngimon activeEngimon,
                  Inventory inventory, Map map) throws NotInitializedException {
        super(x,y,OccupierType.Player_Type, map);
        init();
        this.activeEngimon = activeEngimon;
        this.inventory = inventory;
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
        healthbar.setImage(new Image("assets/life" + getLife() + ".png"));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ActiveEngimon getActiveEngimon() {
        return activeEngimon;
    }

    public void breeding(){
        if (inventory.isEngimonBagEmpty()){
            //Print inv kosong
        }
        else if (inventory.isFull()){
            //Print inv penuh
        }
        else{
            inventory.printAllEngimonInfo();

            Bag<Engimon> temp = inventory.listEngimon();
            //input e1
            //input e2
//            Engimon e1 = temp.list;
//            Engimon e2 = temp.list;
//            if(e1.getLevel() <4 || e2.getLevel()<4  ){
//                //Print level tidak cukup
//                return ;
//            }
//            e1.setLevel(e1.getLevel() - 3);
//            e2.setLevel(e2.getLevel() - 3);

            //Input nama anak

//            Engimon anak = new Engimon(input, e1, e2);
//            inventory.addEngimon(anak);
        }
    }

    @Override
    public boolean setPositionOcc(int x, int y) {
        if (Position.isValidCoordinate(x,y))
        {
            Position posisiBaru = new Position(x,y);
            if (map.getCellAtPosition(posisiBaru).occupier == null
                    || map.getCellAtPosition(posisiBaru).occupier.occupierType == OccupierType.Pet_Type)
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
                musicPlayer.play();
                return true;
            }
        }
        return false;
    }

}
