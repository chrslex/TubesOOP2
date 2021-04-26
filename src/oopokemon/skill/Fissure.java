package oopokemon.skill;

public class Fissure extends Skill {

    public Fissure() {
        super("Fissure", "Ground", 12, 1);
    }
    
    public Fissure(int masteryLevel) {
        super("Fissure", "Ground", 12, masteryLevel);
    }
}