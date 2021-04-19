package oopokemon.map;

public enum CellType {
    Sea_Cell("sea"),
    Grassland_Cell("grass"),
    Mountain_Cell("mountain"),
    Tundra_Cell("tundra");

    private final String value;

    CellType(String value) {
        this.value = value;
    }

    public String getClip(){
        return "bin/music/" + this.value + ".mp3";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
