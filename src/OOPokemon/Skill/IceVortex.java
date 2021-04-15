public class IceVortex extends Skill {
    private String species;
    
    public IceVortex() {
        super("Ice Vortex", "Ice", 13, 1);
        this.species = "Articuno";
    }
    public IceVortex(String species, int masteryLevel) {
        super("Ice Vortex", "Ice", 13, masteryLevel);
        this.species = species;
    }
}