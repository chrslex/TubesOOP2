package OOPokemon.misc;

import OOPokemon.Map.Cell;
import OOPokemon.Map.Map;
import OOPokemon.Occupier.Player;
import OOPokemon.exception.NotInitializedException;
import com.google.gson.*;


import java.io.*;

public class GameState {
    private static float cellWidth = 100;
    private static float cellHeight = 100;
    private static int numOfCellHoriz = 10;
    private static int numOfCellVert = 6;
    private static double musicVol = 0.5;
    private static double sfxVol = 1;

    public Map map;
    public Player player;


    public GameState() {
        String mapFile = "bin/map1.txt";
        map = new Map(mapFile);
        try {
            player = new Player(3,3, map);
        } catch (NotInitializedException e) {
            System.err.println(e.getErrorMessage());
        }
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
}

