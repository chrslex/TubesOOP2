package OOPokemon.Map;

public enum CellType {
    Sea_Cell {
        @Override
        public String toString(){
            return "bin/music/sea.mp3";
        }
    },
    Grassland_Cell {
        @Override
        public String toString(){
            return "bin/music/grass.mp3";
        }
    },
    Mountain_Cell{
        @Override
        public String toString(){
            return "bin/music/mountain.mp3";
        }
    },
    Tundra_Cell{
        @Override
        public String toString(){
            return "bin/music/tundra.mp3";
        }
    },
    Rancu{
        @Override
        public String toString(){
            return "bin/music/grass.mp3";
        }
    }

}
