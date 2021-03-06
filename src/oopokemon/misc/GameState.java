package oopokemon.misc;

import javafx.scene.layout.Pane;
import oopokemon.element.Element;
import oopokemon.inventory.Inventory;
import oopokemon.map.Map;
import oopokemon.map.Position;
import oopokemon.occupier.*;
import oopokemon.skill.Skill;
import oopokemon.species.Engimon;
import oopokemon.exception.NotInitializedException;
import com.google.gson.*;



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameState {
    private static float cellWidth = 100;
    private static float cellHeight = 100;
    private static int numOfCellHoriz = 10;
    private static int numOfCellVert = 6;
    private static double musicVol = 0.5;
    private static double sfxVol = 1;

    public Map map;
    public Player player;
    public EnemyHandler enemyhandler;


    public GameState(Pane mapContainer) {
        String mapFile = "bin/map1.txt";
        map = new Map(mapFile);
        try {
            player = new Player(3,3, map);
            enemyhandler = new EnemyHandler(map, 40, mapContainer);
        } catch (NotInitializedException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    private GameState(Map map, Player player, EnemyHandler enemyHandler, Pane mapContainer){
        this.map = map;
        this.player = player;
        this.enemyhandler = enemyHandler;
    }

    public static void loadConfig() {
        File input = new File("bin/config.json");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            // Mengambil data
            if (fileObject.has("cellWidth")) {
                cellWidth = fileObject.get("cellWidth").getAsFloat();
            }
            if (fileObject.has("cellHeight")) {
                cellHeight = fileObject.get("cellHeight").getAsFloat();
            }
            if (fileObject.has("numOfCellHoriz")) {
                numOfCellHoriz = fileObject.get("numOfCellHoriz").getAsInt();
            }
            if (fileObject.has("numOfCellVert")) {
                numOfCellVert = fileObject.get("numOfCellVert").getAsInt();
            }
            if (fileObject.has("musicVol")) {
                musicVol = fileObject.get("musicVol").getAsDouble();
            }
            if (fileObject.has("sfxVol")) {
                sfxVol = fileObject.get("sfxVol").getAsDouble();
            }
            MusicPlayer.MusicType.BGM.setVolume(musicVol);
            MusicPlayer.MusicType.SFX.setVolume(sfxVol);

        }
        catch (FileNotFoundException e) {
            System.out.println("Gagal Memuat config file, permainan akan berjalan sesuai konfigurasi default");
        }
    }


    public static float getCellWidth() {
        return cellWidth;
    }

    public static float getCellHeight() {
        return cellHeight;
    }

    public static int getNumOfCellHoriz() {
        return numOfCellHoriz;
    }

    public static int getNumOfCellVert() {
        return numOfCellVert;
    }

    public static float getCameraWidth() {
        return numOfCellHoriz * cellWidth;
    }

    public static float getCameraHeight() {
        return numOfCellVert * cellHeight;
    }

    public static double getMusicVol() {
        return musicVol;
    }

    public static double getSfxVol() {
        return sfxVol;
    }

    private static class Config{
        public final float cellWidth = getCellWidth();
        public final float cellHeight = getCellHeight();
        public final int numOfCellHoriz = getNumOfCellHoriz();
        public final int numOfCellVert = getNumOfCellVert();
        public final double musicVol = getMusicVol();
        public final double sfxVol = getSfxVol();
        Config(){}
    }

    public static void saveConfig(int hcell, int vcell, double _musicVol, double _sfxVol) {
        numOfCellHoriz = hcell;
        numOfCellVert = vcell;
        musicVol = _musicVol;
        sfxVol = _sfxVol;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new GameState.Config());

        try {
            FileWriter myWriter = new FileWriter("bin/config.json");
            myWriter.write(json);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static String formatter(String string){
        return "\"" + string + "\"";
    }

    public static GameState loadGame(String source, Pane mapContainer) throws NotInitializedException {
        File input = new File("bin/savefiles/" + source +".json");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            Map map;
            Player player;
            List<Enemy> enemyList = new ArrayList<>();
            EnemyHandler enemyHandler;

            // Mengambil data
            if (fileObject.has("map")) {
                String mapfile = fileObject.get("map").getAsString();
                map = new Map(mapfile);
            }
            else throw new NotInitializedException();
            if (fileObject.has("player")) {
                JsonObject playerObject = fileObject.get("player").getAsJsonObject();
                Position playerpos = new Gson().fromJson(playerObject.get("position").getAsJsonObject(), Position.class);

                ActiveEngimon activeEngimon = new ActiveEngimon(map);

                Inventory inventory = new Inventory();

                Engimon currentEngimon = null;

                if (fileObject.has("activeEngimon")){
                    JsonObject aeObject = fileObject.get("activeEngimon").getAsJsonObject();
                    Position aePos = new Gson().fromJson(aeObject.get("position").getAsJsonObject(), Position.class);
                    activeEngimon.setPositionOcc(aePos.x, aePos.y);

                    if (aeObject.has("engimon")) {
                        JsonObject engimonObj = aeObject.get("engimon").getAsJsonObject();
                        currentEngimon = Engimon.fromJson(engimonObj);
                        inventory.addEngimon(currentEngimon);
                    }
                }

                if (fileObject.has("inventory")){
                    JsonObject invObj = fileObject.get("inventory").getAsJsonObject();
                    if (invObj.has("engimonList")){
                        JsonArray engimonArray = invObj.get("engimonList").getAsJsonArray();
                        for (JsonElement engimonElement : engimonArray){
                            JsonObject engimonJSONObj = engimonElement.getAsJsonObject();
                            Engimon playerEngimon = Engimon.fromJson(engimonJSONObj);
                            inventory.addEngimon(playerEngimon);
                        }
                    }
                    if (invObj.has("skillList")){
                        JsonArray skillString = invObj.get("skillList").getAsJsonArray();
                        Skill[] skills = new Gson().fromJson(skillString, Skill[].class);
                        for (Skill skillItem : skills){
                            inventory.addSkill(skillItem);
                        }
                    }
                }

                player = new Player(playerpos.x, playerpos.y, activeEngimon, inventory, map);
                player.setActiveEngimon(currentEngimon);
//                ActiveEngimon activeEngimon = new ActiveEngimon()

            }
            else throw new NotInitializedException();
            if (fileObject.has("enemies")) {
                JsonArray jsonArray = fileObject.getAsJsonArray("enemies");
                for (JsonElement enm : jsonArray) {
                    JsonObject enobj = enm.getAsJsonObject();
                    Position enemypos = new Gson().fromJson(enobj.get("position").getAsJsonObject(), Position.class);
                    int baselvl = enobj.get("level").getAsInt();
                    int jenis = Engimon.getTypeInt(enobj.get("type").getAsString());
                    int exp = enobj.get("exp").getAsInt();
                    Enemy enemy = new Enemy(map,jenis,baselvl);
                    enemy.setPos(enemypos.x, enemypos.y);
                    enemy.setExp(exp);
                    enemyList.add(enemy);
                }
                if (enemyList.size() > 0){
                    enemyHandler = new EnemyHandler(enemyList, mapContainer);
                }
                else throw new NotInitializedException();
            }
            else throw new NotInitializedException();
            System.out.println("Berhasil Load Game");
            return new GameState(map, player, enemyHandler, mapContainer);
        }

        catch (FileNotFoundException e) {
            throw new NotInitializedException();
        }
    }

    public void saveGame(String saveDest) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = "{\n\"map\":" + formatter(map.namaFile);
        // Player
        json += String.format(",\n%s:{\n%s:\n%s}", formatter("player"), formatter("position"), gson.toJson(player.position));

        ActiveEngimon activeEngimon = player.getActiveEngimon();

        if (activeEngimon.getEngimon() != null) {
            json += String.format(", \n%s:{\n%s:\n%s , \n%s:%s}",
                    formatter("activeEngimon"), formatter("position"), gson.toJson(activeEngimon.position),
                    formatter("engimon"), activeEngimon.getEngimon().toJson());
        } else {
            json += String.format(", \n%s:{\n%s:\n%s}",
                    formatter("activeEngimon"), formatter("position"), gson.toJson(activeEngimon.position));
        }
        if (!player.getInventory().isEmpty()){
            StringBuilder engimons = new StringBuilder();
            for (Engimon engimon : player.getInventory().listEngimon()){
                if (engimon != player.getEngimon()){
                    engimons.append(engimon.toJson()).append(",");
                }
            }
            if (engimons.length() > 0){
                engimons = new StringBuilder(engimons.substring(0, engimons.length() - 1));
            }
            StringBuilder skillString = new StringBuilder();
            for (Skill skill : player.getInventory().listAllSkill()){
                String skill1 = gson.toJson(skill);
                skillString.append(skill1).append(",");
            }
            if (skillString.length() > 0){
                skillString = new StringBuilder(skillString.substring(0, skillString.length() - 1));
            }
            json += String.format(", \n%s:{\n%s:[\n%s] , \n%s:[\n%s]}",
                    formatter("inventory"),formatter("engimonList"), engimons,
                    formatter("skillList"), skillString);

        }

        List<Enemy> enemyList = map.getCells().stream()
                .filter(cell -> cell.occupier != null && cell.occupier.occupierType == OccupierType.Enemy_Type)
                .map(cell -> (Enemy) cell.occupier).collect(Collectors.toList());

        StringBuilder enemystr = new StringBuilder();
        for (Enemy enemy: enemyList) {
//            {"type": 1, "level": 1, "position": {"x": 1, "y": 2}}
            String enemiess = String.format("{%s:%s, %s:%d, %s:%d, %s:%s},",
                    formatter("type"), formatter(enemy.getEngimon().getNamaSpecies()),
                    formatter("level"), enemy.getEngimon().getBaseLevel(),
                    formatter("exp"), enemy.getEngimon().getExp(),
                    formatter("position"), gson.toJson(enemy.position));
            enemystr.append(enemiess);
        }
        if (enemystr.length() > 0){
            enemystr = new StringBuilder(enemystr.substring(0, enemystr.length() - 1));
            json += String.format(",%s:[%s]", formatter("enemies"), enemystr.toString());
        }

        json+= "\n}";

        try {
            FileWriter myWriter = new FileWriter("bin/savefiles/" + saveDest + ".json");
            myWriter.write(json);
            myWriter.close();
            AlertBox.display("Saving", "Berhasil Menyimpan Game");
        } catch (IOException e) {
            AlertBox.display("Saving", "Gagal Menyimpan Game");
            e.printStackTrace();
        } catch (Exception e){
            AlertBox.display("Saving", "Gagal Menyimpan Game");
        }
    }

}

