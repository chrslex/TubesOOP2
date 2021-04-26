package oopokemon.skill;

public class Torrent extends Skill {

    public Torrent() {
        super("Torrent", "Water", 15, 1);
    }
    public Torrent(int masteryLevel) {
        super("Torrent", "Water", 12, masteryLevel);
    }
}