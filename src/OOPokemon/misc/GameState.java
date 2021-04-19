package OOPokemon.misc;

import OOPokemon.Map.Cell;
import OOPokemon.Map.CellType;
import OOPokemon.Map.Map;
import OOPokemon.Map.Position;
import OOPokemon.Occupier.*;
import OOPokemon.Species.Engimon;
import OOPokemon.exception.NotInitializedException;
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


    public GameState() {
        String mapFile = "bin/map1.txt";
        map = new Map(mapFile);
        try {
            player = new Player(3,3, map);
            enemyhandler = new EnemyHandler(map, 40);
        } catch (NotInitializedException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    private GameState(Map map, Player player, EnemyHandler enemyHandler){
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
            FileWriter myWriter = new FileWriter("bin/config2.json");
            myWriter.write(json);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static String formater(String string){
        return "\"" + string + "\"";
    }

    public static GameState loadGame(String source) throws NotInitializedException {
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
                player = new Player(playerpos.x, playerpos.y, map);

            }
            else throw new NotInitializedException();
            if (fileObject.has("enemies")) {
                JsonArray jsonArray = fileObject.getAsJsonArray("enemies");
                for (JsonElement enm : jsonArray) {
                    JsonObject enobj = enm.getAsJsonObject();
                    Position enemypos = new Gson().fromJson(enobj.get("position").getAsJsonObject(), Position.class);
                    int lvl = enobj.get("level").getAsInt();
                    int jenis = Engimon.getTypeInt(enobj.get("type").getAsString());
                    Enemy enemy = new Enemy(map,jenis,lvl);
                    enemy.setPos(enemypos.x, enemypos.y);
                    enemyList.add(enemy);
                }
                if (enemyList.size() > 0){
                    enemyHandler = new EnemyHandler(enemyList);
                }
                else throw new NotInitializedException();
            }
            else throw new NotInitializedException();
            System.out.println("Berhasil load");
            return new GameState(map, player, enemyHandler);
        }

        catch (FileNotFoundException e) {
            throw new NotInitializedException();
        }
    }

    public void saveGame(String saveDest) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = "{\"map\":" + formater(map.namaFile);
        // Player
        json += String.format(",%s:{%s:%s}", formater("player"), formater("position"), gson.toJson(player.position));

        List<Enemy> enemyList = map.getMap().stream()
                .filter(cell -> cell.occupier != null && cell.occupier.occupierType == OccupierType.Enemy_Type)
                .map(cell -> (Enemy) cell.occupier).collect(Collectors.toList());

        StringBuilder enemystr = new StringBuilder();
        for (Enemy enemy: enemyList) {
//            {"type": 1, "level": 1, "position": {"x": 1, "y": 2}}
            String enemiess = String.format("{%s:%s, %s:%d, %s:%s},",
                    formater("type"), formater(enemy.getEngimon().getNamaSpecies()),
                    formater("level"), enemy.getEngimon().getLevel(),
                    formater("position"), gson.toJson(enemy.position));
            enemystr.append(enemiess);
        }
        if (enemystr.length() > 0){
            enemystr = new StringBuilder(enemystr.substring(0, enemystr.length() - 1));
            json += String.format(",%s:[%s]",formater("enemies"), enemystr.toString());
        }

        json+= "}";

        try {
            FileWriter myWriter = new FileWriter("bin/savefiles/" + saveDest + ".json");
            myWriter.write(json);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

