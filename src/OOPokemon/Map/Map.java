package OOPokemon.Map;

import OOPokemon.Occupier.Occupier;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    List<Cell> cells;
    int MAX_X = Position.MAX_X;
    int MAX_Y = Position.MAX_Y;


    public Map(Pane root) {
        cells = new ArrayList<>();
        for (int i = 0; i < MAX_X * MAX_Y; i++) {
            Cell cell = new Cell(i % MAX_X, i / MAX_X, CellType.Grassland_Cell);
            root.getChildren().add(cell);
            cells.add(cell);
        }
    }

    public Map(String fileName, Pane root) {
        cells = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            MAX_X = (line != null)? line.length() : 0;
            Position.MAX_X = MAX_X;
            MAX_Y = 0;
            while (line != null){
                for (int i = 0; i < MAX_X ; i++) {
                    try {
                        char c = line.charAt(i);
                        CellType cellType = (c == 'o')? CellType.Sea_Cell : CellType.Grassland_Cell;
                        Cell cell = new Cell(i, MAX_Y, cellType);
                        root.getChildren().add(cell);
                        cells.add(cell);
                    }
                    catch (StringIndexOutOfBoundsException e){
                        Cell cell = new Cell(i, MAX_Y, CellType.Grassland_Cell);
                        root.getChildren().add(cell);
                        cells.add(cell);
                    }
                }
                MAX_Y++;
                line = reader.readLine();
            }
            Position.MAX_Y = MAX_Y;
        }
        catch (IOException e) {
            System.out.println("Map tidak ditemukan");
        }
    }

    public Cell getCellAtPosition(Position position) {
        return cells.get(position.x + position.y * MAX_X);
    }

    public boolean addOccupier(Occupier occupier) {
        Cell temp = getCellAtPosition(occupier.position);
        if (temp.occupier != null) return false;
        temp.occupier = occupier;
        return true;
    }

    public void removeOccupierAtPosition(Position position) {
        getCellAtPosition(position).occupier = null;
    }



}