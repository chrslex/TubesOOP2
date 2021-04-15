package OOPokemon.Skill;

public class Nimbus extends Skill {
    private String species;
    
    public Nimbus() {
        super("Nimbus", "Electric", 16, 1);
        this.species = "None";
    }
    public Nimbus(String species, int masteryLevel) {
        super("Nimbus", "Electric", 16, masteryLevel);
        this.species = species;
    }
}