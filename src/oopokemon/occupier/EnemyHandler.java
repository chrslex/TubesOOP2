package oopokemon.occupier;

import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnemyHandler implements Runnable {
    private List<Enemy> enemyList;
    private AtomicBoolean want_to_susp = new AtomicBoolean(false);
    private Thread t;

    private int interval = 500;

    public EnemyHandler(Map map, int size) throws NotInitializedException {
        this.enemyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            enemyList.add(new Enemy(map, rand.nextInt(8),1));
        }
        t = new Thread(this);
        t.start();
    }

    public EnemyHandler(List<Enemy> enemyList) {
        this.enemyList = enemyList;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        moveAllRandom();
    }


    public void moveAllRandom() {
        while (true) {
//            enemyList.forEach(enemy -> enemy.move(new Random().nextInt(4)));
            for ( Enemy enemy: enemyList) {
                enemy.move(new Random().nextInt(4));
            }
            try {
                Thread.sleep(interval);
                synchronized (this){
                    while (want_to_susp.get()){
                        wait();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void suspend() {
        want_to_susp.set(true);
    }

    public synchronized void resume(){
        want_to_susp.set(false);
        notify();
    }


    public void setInterval(int interval) {
        if (interval >= 1000){
            this.interval = interval;
        }
    }


}
