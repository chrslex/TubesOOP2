package oopokemon.species;

import oopokemon.skill.Fissure;
import oopokemon.skill.Torrent;

import static oopokemon.element.ElementType.*;

public class Seismotoad extends Engimon {
    private void InitComp() {
        namaSpecies = "Seismotoad";
        monElements[0].setElementType(Water);
        monElements[1].setElementType(Ground);
        monSkills[0] = new Torrent();
        monSkills[1] = new Fissure();
        imageSource = "assets/seismotoad.png";
    }

    public Seismotoad() {
        super();
        InitComp();
    }

    public Seismotoad(String name) {
        super(name);
        InitComp();
    }
}
