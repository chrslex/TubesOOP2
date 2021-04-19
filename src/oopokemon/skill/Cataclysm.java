package oopokemon.skill;

public class Cataclysm extends Skill {
    private final String species;
    
    public Cataclysm() {
        super("Cataclysm", "Fire", 15, 1);
        this.species  = "None";
    }
    
    public Cataclysm(String species, int masteryLevel) {
        super("Cataclysm", "Fire", 18, masteryLevel);
        this.species = species;
    }
}