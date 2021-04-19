package oopokemon.skill;

public class Torrent extends Skill {
    private String species;
    
    public Torrent() {
        super("Torrent", "Water", 15, 1);
        this.species = "Squirtle";
    }
    public Torrent(String species, int masteryLevel) {
        super("Torrent", "Water", 12, masteryLevel);
        this.species = species;
    }
}