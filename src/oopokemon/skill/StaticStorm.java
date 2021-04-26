package oopokemon.skill;

public class StaticStorm extends Skill {

    public StaticStorm() {
        super("Static Storm", "Electric", 14, 1);
    }
    public StaticStorm(int masteryLevel) {
        super("Static Storm", "Electric", 14, masteryLevel);
    }
}