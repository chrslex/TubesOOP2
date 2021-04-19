package oopokemon.skill;

public class Sunstrike extends Skill {
    private String species;
    
    public Sunstrike() {
        super("Sunstrike", "Fire", 15, 1);
        this.species = "Dragon";
    }
    public Sunstrike(String species, int masteryLevel) {
        super("Sunstrike", "Fire", 15, masteryLevel);
        this.species = species;
    }
}