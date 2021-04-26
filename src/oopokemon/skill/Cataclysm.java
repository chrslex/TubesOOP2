package oopokemon.skill;

public class Cataclysm extends Skill {

    public Cataclysm() {
        super("Cataclysm", "Fire", 18, 1);
    }
    
    public Cataclysm(int masteryLevel) {
        super("Cataclysm", "Fire", 18, masteryLevel);
    }
}