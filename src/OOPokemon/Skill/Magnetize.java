package OOPokemon.Skill;

public class Magnetize extends Skill {
    private String species;
    
    public Magnetize() {
        super("Magnetize", "Ground", 15, 1);
        this.species = "Excadrill";
    }
    public Magnetize(String species, int masteryLevel) {
        super("Magnetize", "Ground", 15, masteryLevel);
        this.species = species;
    }
}