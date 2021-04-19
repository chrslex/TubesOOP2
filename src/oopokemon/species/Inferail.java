package oopokemon.species;

import oopokemon.skill.StaticStorm;
import oopokemon.skill.Sunstrike;

import static oopokemon.Element.ElementType.*;

public class Inferail extends Engimon {
    private void InitComp() {
        namaSpecies = "Inferail";
        monElements[0].setElementType(Fire);
        monElements[1].setElementType(Electric);
        monSkills[0] = new Sunstrike();
        monSkills[1] = new StaticStorm();
        imageSource = "assets/inferail.png";

    }

    public Inferail() {
        super();
        InitComp();
    }

    public Inferail(String name) {
        super(name);
        InitComp();
    }
}
