package oopokemon.skill;

public class Sunstrike extends Skill {

    public Sunstrike() {
        super("Sunstrike", "Fire", 15, 1);
    }
    public Sunstrike(int masteryLevel) {
        super("Sunstrike", "Fire", 15, masteryLevel);
    }
}