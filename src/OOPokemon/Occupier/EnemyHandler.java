package OOPokemon.Occupier;

import OOPokemon.Map.Map;
import OOPokemon.exception.NotInitializedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyHandler {
    private List<Enemy> enemyList;

    public EnemyHandler(Map map, int size) throws NotInitializedException {
        this.enemyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            enemyList.add(new Enemy(map, rand.nextInt(8),1));
        }

    }
}
