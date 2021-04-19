package oopokemon.species;

import oopokemon.skill.Sunstrike;

import static oopokemon.element.ElementType.Fire;

public class Dragon extends Engimon {
    private void InitComp() {
        namaSpecies = "Dragon";
        monElements[0].setElementType(Fire);
        monSkills[0] = new Sunstrike();
        imageSource = "assets/dragon.png";

    }

    public Dragon() {
        super();
        InitComp();
    }

    public Dragon(String name) {
        super(name);
        InitComp();
    }
}
