package oopokemon.map;

import oopokemon.occupier.Occupier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private final List<Cell> cells;
    int maxX = Position.MAX_X;
    int maxY = Position.MAX_Y;
    public String namaFile;


    public Map() {
        namaFile = "";
        cells = new ArrayList<>();
        for (int i = 0; i < maxX * maxY; i++) {
            Cell cell = new Cell(i % maxX, i / maxX, CellType.Grassland_Cell);
            cells.add(cell);
        }
    }

    public Map(String fileName) {
        namaFile = fileName;
        cells = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            maxX = (line != null)? line.length() : 0;
            Position.MAX_X = maxX;
            maxY = 0;
            while (line != null){
                for (int i = 0; i < maxX; i++) {
                    try {
                        char c = line.charAt(i);
                        CellType cellType;
                        switch (c){
                            case '^':
                                cellType = CellType.Mountain_Cell;
                                break;
                            case '=':
                                cellType = CellType.Tundra_Cell;
                                break;
                            case 'o':
                                cellType = CellType.Sea_Cell;
                                break;
                            default:
                                cellType = CellType.Grassland_Cell;
                        }
                        Cell cell = new Cell(i, maxY, cellType);
                        cells.add(cell);
                    }
                    catch (StringIndexOutOfBoundsException e){
                        Cell cell = new Cell(i, maxY, CellType.Grassland_Cell);
                        cells.add(cell);
                    }
                }
                maxY++;
                line = reader.readLine();
            }
            Position.MAX_Y = maxY;
        }
        catch (IOException e) {
            System.out.println("map tidak ditemukan");
        }
    }

    public Cell getCellAtPosition(Position position) {
        return cells.get(position.x + position.y * maxX);
    }

    public void addOccupier(Occupier occupier) {
        Cell temp = getCellAtPosition(occupier.position);
        if (temp.occupier != null) return;
        temp.occupier = occupier;
    }

    public void removeOccupierAtPosition(Position position) {
        getCellAtPosition(position).occupier = null;
    }

    public List<Cell> getMap(){
        return cells;
    }



}
