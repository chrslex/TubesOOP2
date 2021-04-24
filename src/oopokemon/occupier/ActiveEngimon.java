package oopokemon.occupier;

import javafx.scene.Node;
import javafx.scene.image.Image;
import oopokemon.exception.NotInitializedException;
import oopokemon.map.Map;
import oopokemon.species.Engimon;

public class ActiveEngimon extends Occupier{
    private Engimon engimon = null;

    public ActiveEngimon(Map map, Engimon engimon) throws NotInitializedException {
        super(map);
        this.occupierType = OccupierType.Pet_Type;
        setEngimon(engimon);
    }

    public ActiveEngimon(int x, int y, Map map, Engimon engimon) throws NotInitializedException {
        super(x, y, OccupierType.Pet_Type, map);
        setEngimon(engimon);
    }

    public ActiveEngimon(Map map) throws NotInitializedException {
        super(map);
        this.occupierType = OccupierType.Pet_Type;
        setEngimon(null);
    }

    public int getLife(){
        if (engimon!=null) return engimon.getLife();
        return 0;
    }

    public int getLevel(){
        if (engimon!=null) return engimon.getLevel();
        return 1;
    }

    public Engimon getEngimon(){
        return engimon;
    }

    public void setEngimon(Engimon engimon) {
        this.engimon = engimon;
        if (engimon == null){
            sprite.setCenterImage(new Image("assets/null.png"));
            sprite.setBottomLeftImage(new Image("assets/null.png"));
            sprite.setBottomRightImage(new Image("assets/null.png"));
            return;
        }
        sprite.setCenterImage(engimon.getImage());
        sprite.setBottomLeftImage(new Image("assets/player2.png"));
        sprite.setBottomRightImage(engimon.getElementImage());
    }

//    @Override
//    public Node getToRender() {
//        if ()
//        return super.getToRender();
//    }

//    public static void main(String[] args) throws NotInitializedException {
//        new ActiveEngimon(new Map()).setEngimon(null);
//    }
}
