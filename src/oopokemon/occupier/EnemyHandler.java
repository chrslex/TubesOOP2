package oopokemon.occupier;

import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnemyHandler implements Runnable {
    private final List<Enemy> enemyList;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean wantToSusp = new AtomicBoolean(false);
    public final Thread thread;

    private int interval = 500;

    public EnemyHandler(Map map, int size) throws NotInitializedException {
        this.enemyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            enemyList.add(new Enemy(map, rand.nextInt(8),1));
        }
        thread = new Thread(this);
        thread.start();
    }

    public EnemyHandler(List<Enemy> enemyList) {
        this.enemyList = enemyList;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        moveAllRandom();
    }


    public void moveAllRandom() {
        running.set(true);
        while (running.get()) {
            try {
                Thread.sleep(interval);
                synchronized (this){
                    while (wantToSusp.get()){
                        wait();
                    }
                    enemyList.forEach(enemy -> enemy.move(new Random().nextInt(4)));
                }

            }
            catch (InterruptedException ignored) {}
        }
    }
    public synchronized void suspend() {
        wantToSusp.set(true);
    }

    public synchronized void resume(){
        wantToSusp.set(false);
        notify();
    }

    public void setInterval(int interval) {
        if (interval >= 1000){
            this.interval = interval;
        }
    }

    public void interrupt(){
        this.running.set(false);
        this.thread.interrupt();
    }

}
