package oopokemon.skill;

public class Fissure extends Skill {
    private final String species;
    
    public Fissure() {
        super("Fissure", "Ground", 12, 1);
        this.species = "Excadrill";
    }
    
    public Fissure(String species, int masteryLevel) {
        super("Fissure", "Ground", 12, masteryLevel);
        this.species = species;
    }
}