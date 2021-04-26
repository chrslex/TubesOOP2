package oopokemon.skill;

public class StaticStorm extends Skill {

    public StaticStorm() {
        super("StaticStorm", "Electric", 14, 1);
    }
    public StaticStorm(int masteryLevel) {
        super("StaticStorm", "Electric", 14, masteryLevel);
    }
}