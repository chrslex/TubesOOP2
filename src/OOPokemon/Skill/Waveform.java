package OOPokemon.Skill;

public class Waveform extends Skill {
    private String species;
    
    public Waveform() {
        super("Waveform", "Water", 17, 1);
        this.species = "None";
    }
    public Waveform(String species, int masteryLevel) {
        super("Waveform", "Water", 17, masteryLevel);
        this.species = species;
    }
}