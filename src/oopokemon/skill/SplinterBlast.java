package oopokemon.skill;

public class SplinterBlast extends Skill {
    private final String species;
    
    public SplinterBlast() {
        super("Splinter Blast", "Ice", 16, 1);
        this.species = "None";
    }
    public SplinterBlast(String species, int masteryLevel) {
        super("Splinter Blast", "Ice", 16, masteryLevel);
        this.species = species;
    }
}