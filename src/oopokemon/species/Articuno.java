package oopokemon.species;

import oopokemon.skill.IceVortex;

import static oopokemon.element.ElementType.Ice;

public class Articuno extends Engimon {
    private void InitComp() {
        namaSpecies = "Articuno";
        monElements[0].setElementType(Ice);
        monSkills[0] = new IceVortex();
        imageSource = "assets/articuno.png";

    }

    public Articuno() {
        super();
        InitComp();
    }

    public Articuno(String name) {
        super(name);
        InitComp();
    }

    @Override
    public String interactions() {
        return "Dingiiiinnn...";
    }
}
