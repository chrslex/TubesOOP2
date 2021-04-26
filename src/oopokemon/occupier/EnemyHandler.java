package oopokemon.occupier;

import javafx.scene.layout.Pane;
import oopokemon.map.Map;
import oopokemon.exception.NotInitializedException;
import oopokemon.misc.Renderer;
import oopokemon.misc.Sprite;
import oopokemon.species.Engimon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnemyHandler implements Runnable {
    private volatile List<Enemy> enemyList;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean wantToSusp = new AtomicBoolean(false);
    public final Thread thread;
    private Player player = null;

    private Pane mapPlaceHolder;
    private volatile int interval = 2000;

    public EnemyHandler(Map map, int initialSize, Pane mapPlaceHolder) throws NotInitializedException {
        this.enemyList = new ArrayList<>();
        Optional<Player> player = map.getCells().stream().filter(cell -> cell.occupier != null
                && cell.occupier.occupierType == OccupierType.Player_Type)
                .map(cell -> (Player) cell.occupier).findFirst();
        int lvl = player.map(Player::getHighestLevel).orElse(1);
        player.ifPresent(value -> this.player = value);
        this.mapPlaceHolder = mapPlaceHolder;
        for (int i = 0; i < initialSize; i++) {
            Random rand = new Random();
            // merandom 0 - 8 untuk jenis engimon, (lvl engimon tertingi + 3) - (lvl engimon tertinggi) untuk level
            enemyList.add(new Enemy(map, rand.nextInt(8),rand.nextInt(3) + lvl));
        }
        thread = new Thread(this);
        thread.start();
    }

    public EnemyHandler(List<Enemy> enemyList, Pane mapPlaceHolder) {
        this.mapPlaceHolder = mapPlaceHolder;
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
                    int playerLvl = player.getLevel();
                    enemyList.forEach(enemy -> {
                        enemy.move(new Random().nextInt(4));
                        int enemyLvl = enemy.getEngimon().getLevel();
                        if (player.getEngimon() != null){
                            float playerMaxElAdv = Engimon.maxElAdv(player.getEngimon(), enemy.getEngimon());
                            float enemyMaxElAdv = Engimon.maxElAdv(enemy.getEngimon(), player.getEngimon());
                            float powerPlayer = (playerLvl * playerMaxElAdv) + player.getEngimon().sumSkillPower();
                            float powerEnemy = (enemyLvl * enemyMaxElAdv) + enemy.getEngimon().sumSkillPower();
                            enemy.setToLowerSize(powerEnemy < powerPlayer);
                        }
                        if ((finalTurnCounter % 10) == 9) {
                            if (!enemy.setExp(100)){
                                Random rand = new Random();
                                enemy.init(rand.nextInt(8), rand.nextInt(3) + player.getHighestLevel());
                                System.out.println("musuh baru spawning");
                            }
                        }
                    });
//                    if (finalTurnCounter % 5 == 4){
//                        Random rand = new Random();
//                        int lvl = player.getHighestLevel();
//                        Enemy newENemy = new Enemy(player.getMap(), rand.nextInt(8), rand.nextInt(3) + lvl);
//                        enemyList.add(newENemy);
//                        Renderer.renderNode(newENemy.getToRender(), mapPlaceHolder);
//                        System.out.println("spawning");
//                    }
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

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}
