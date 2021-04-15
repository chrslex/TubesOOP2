package OOPokemon.Skill;

public class Cataclysm extends Skill {
    private String species;
    
    public Cataclysm() {
        super("Cataclysm", "Fire", 15, 1);
        this.species  = "None";
    }
    
    public Cataclysm(String species, int masteryLevel) {
        super("Cataclysm", "Fire", 18, masteryLevel);
        this.species = species;
    }
}