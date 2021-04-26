package oopokemon.species;

import oopokemon.skill.Torrent;

import static oopokemon.element.ElementType.Water;

public class Squirtle extends Engimon {
    private void InitComp() {
        namaSpecies = "Squirtle";
        monElements[0].setElementType(Water);
        monSkills[0] = new Torrent();
        imageSource = "assets/squirtle.png";
    }

    public Squirtle() {
        super();
        InitComp();
    }

    public Squirtle(String name) {
        super(name);
        InitComp();
    }

    @Override
    public String interactions() {
        return "Lambat tapi kuat...";
    }
}
