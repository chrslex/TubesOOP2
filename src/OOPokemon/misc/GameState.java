package OOPokemon.misc;

import OOPokemon.Map.Map;
import OOPokemon.Occupier.Player;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.layout.Pane;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class GameState {

    private static float cellWidth = 100;
    private static float cellHeight = 100;
    private static int numOfCellHoriz = 10;
    private static int numOfCellVert = 6;


    public Map map;
    public Player player;


    public GameState(Pane mapContainer){
        String mapFile = "bin/map1.txt";
        map = new Map(mapFile, mapContainer);
        player = new Player(3,3, mapContainer, map);
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
            if (fileObject.has("numOfCellHorizontal")) {
                numOfCellHoriz = fileObject.get("numOfCellHorizontal").getAsInt();
            }
            if (fileObject.has("numOfCellVertical")) {
                numOfCellVert = fileObject.get("numOfCellVertical").getAsInt();
            }

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

}
