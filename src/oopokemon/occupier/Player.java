package oopokemon.occupier;

import javafx.scene.image.ImageView;
import oopokemon.inventory.Bag;
import oopokemon.inventory.Inventory;
import oopokemon.inventory.InventoryGUI;
import oopokemon.map.Cell;
import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;
import oopokemon.map.Position;
import oopokemon.misc.AlertBox;
import oopokemon.misc.InputBox;
import oopokemon.misc.MusicPlayer;
import javafx.scene.image.Image;
import oopokemon.skill.*;
import oopokemon.species.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Occupier {

    private final Inventory inventory;
    private final ActiveEngimon activeEngimon;

    private ImageView healthbar = new ImageView(new Image("assets/life0.png"));

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
        Dragon dragon = new Dragon("okee");
        setActiveEngimon(dragon);
        activeEngimon.setPositionOcc(x-1, y);
        inventory = new Inventory();
        inventory.addEngimon(dragon);
        Engimon engimon = new Inferail("infer");
        inventory.addEngimon(engimon);
        Engimon kyogre = new Kyogre("ogre");
        kyogre.setLevel(5);
        dragon.setLevel(10);
        inventory.addEngimon(kyogre);
        inventory.addEngimon(new Seismotoad("seismo"));
        inventory.addEngimon(new Seismotoad("seismo"));
        inventory.addEngimon(new Seismotoad("seismo"));
        inventory.addEngimon(new Seismotoad("seismo"));
//        inventory.addEngimon(new Seismotoad("seismo"));
//        inventory.addEngimon(new Excadrill("driller"));
//        inventory.addEngimon(new Excadrill("driller"));
//        inventory.addEngimon(new Excadrill("driller"));
//        inventory.addEngimon(new Excadrill("driller"));
        inventory.addEngimon(new Excadrill("driller"));
        inventory.addEngimon(new Raichu("raisa"));
        inventory.addEngimon(new Raichu("raisa"));
        inventory.addEngimon(new Raichu("raisa"));
        Raichu raiso = new Raichu("raisa");
        raiso.setLevel(10);
        inventory.addEngimon(raiso);
        Inferail infer = new Inferail("infer");
        infer.setLevel(10);
        inventory.addEngimon(infer);
        inventory.addSkill(new SplinterBlast());
        inventory.addSkill(new Torrent());
        inventory.addSkill(new SplinterBlast());
        inventory.addSkill(new SplinterBlast());
        inventory.addSkill(new Torrent());
        inventory.addSkill(new StaticStorm());
        inventory.addSkill(new StaticStorm());
        inventory.addSkill(new StaticStorm());
        inventory.addSkill(new Sunstrike());
        inventory.addSkill(new IceVortex());
        inventory.addSkill(new Cataclysm());
        inventory.addSkill(new Fissure());
        inventory.addSkill(new Magnetize());
        inventory.addSkill(new Nimbus());
        inventory.addSkill(new StaticStorm());
        inventory.addSkill(new Waveform());
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

    public void interacts() {
        if (this.getEngimon() != null) {
            AlertBox.display("Interaction", this.getEngimon().getName() + ":\n" + this.getEngimon().interactions());
        }
    }

    public void openInventory(){
        InventoryGUI.createInventory(this, InventoryGUI.InventoryType.ENGIMON);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Engimon getEngimon(){
        return activeEngimon.getEngimon();
    }

    public ActiveEngimon getActiveEngimon() {
        return activeEngimon;
    }

    public void breeding(){
        if (inventory.isEngimonBagEmpty()){
            AlertBox.display("Breeding", "Inventory Kosong");
        }
        else if (inventory.isFull()){
            //Print inv penuh
            AlertBox.display("Breeding", "Inventory Penuh");
        }
        else{
            inventory.printAllEngimonInfo();
            List<Engimon> listEngimon = inventory.listEngimon();
            Integer input1;
            Integer input2;
            Engimon engimon1;
            Engimon engimon2;
            do {
                input1 = InputBox.inputPrompt("Breeding", "Pilih Engimon 1");
                try {
                    if (input1 == -1) {
                        return;
                    }
                    engimon1 = listEngimon.get(input1);
                }
                catch (NullPointerException | IndexOutOfBoundsException e){
                    engimon1 = null;
                }
            }while (input1 == null || engimon1 == null);
            do {
                input2 = InputBox.inputPrompt("Breeding", "Pilih Engimon 2");
                try {
                    if (input2 == -1) {
                        return;
                    }
                    engimon2 = listEngimon.get(input2);
                    if (engimon1 == engimon2){
                        AlertBox.display("Breeding", "Jenis Engimon 1 dan Engimon 2 Sama\nBreeding Dibatalkan");
                        return;
                    }
                }
                catch (NullPointerException | IndexOutOfBoundsException e){
                    engimon2 = null;
                }
            }while (input2 == null || engimon2 == null);

            if(engimon1.getLevel() <4 || engimon2.getLevel()<4  ){
                AlertBox.display("Breeding", "Level Engimon Tidak Mencukupi");
                return;
            }
            engimon1.setLevel(engimon1.getLevel() - 3);
            engimon1.setLevel(engimon2.getLevel() - 3);


            Engimon anak = new Engimon(InputBox.inputName("Breeding","Beri Nama Anak :"), engimon1, engimon2);
            //Input nama anak
//            Engimon anak = new Engimon(input, e1, e2);
            inventory.addEngimon(anak);
        }
    }
    public ImageView getHealthbar(){
        return healthbar;
    }
    
    public Engimon getClosestEnemy(){
//        List<Enemy> enemies = new ArrayList<>();
        for (int i = position.x-1; i <= position.x+1 ; i++) {
            for (int j = position.y-1; j <= position.y+1; j++) {
                if (Position.isValidCoordinate(i,j)){
                    Position position = new Position(i,j);
                    Cell tempCell =  map.getCellAtPosition(position);
                    if (tempCell.occupier != null
                            && tempCell.occupier.occupierType == OccupierType.Enemy_Type){
                        Enemy enemy = (Enemy)tempCell.occupier;
                        return enemy.getEngimon();
                    }
                }
            }
        }
        return null;
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
