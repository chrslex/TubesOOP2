package oopokemon.occupier;

import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnemyHandler implements Runnable {
    private final List<Enemy> enemyList;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean wantToSusp = new AtomicBoolean(false);
    public final Thread thread;

    private Player player = null;

    private volatile int interval = 500;

    public EnemyHandler(Map map, int size) throws NotInitializedException {
        this.enemyList = new ArrayList<>();
        Optional<Player> player = map.getCells().stream().filter(cell -> cell.occupier != null
                && cell.occupier.occupierType == OccupierType.Player_Type)
                .map(cell -> (Player) cell.occupier).findFirst();
        int lvl = player.map(Player::getHighestLevel).orElse(1);
        player.ifPresent(value -> this.player = value);
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            // merandom 0 - 8 untuk jenis engimon, (lvl engimon tertingi + 3) - (lvl engimon tertinggi) untuk level
            enemyList.add(new Enemy(map, rand.nextInt(8),rand.nextInt(3) + lvl));
        }
        thread = new Thread(this);
        thread.start();
    }

    public EnemyHandler(List<Enemy> enemyList) {
        this.enemyList = enemyList;
        Optional<Player> player = enemyList.get(0).getMap().getCells().stream().filter(cell -> cell.occupier != null
                && cell.occupier.occupierType == OccupierType.Player_Type)
                .map(cell -> (Player) cell.occupier).findFirst();
        player.ifPresent(value -> this.player = value);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        moveAllRandom();
    }


    public void moveAllRandom() {
        running.set(true);
        int turnCounter = 0;
        while (running.get()) {
            try {
                Thread.sleep(interval);
                synchronized (this){
                    while (wantToSusp.get()){
                        wait();
                    }
                    int finalTurnCounter = turnCounter;
                    enemyList.forEach(enemy -> {
                        enemy.move(new Random().nextInt(4));
                        if ((finalTurnCounter % 10) == 9) enemy.setExp(100);
                    });
                    turnCounter++;
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
