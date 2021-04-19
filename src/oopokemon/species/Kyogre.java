package oopokemon.species;

import oopokemon.skill.IceVortex;
import oopokemon.skill.Torrent;

import static oopokemon.Element.ElementType.*;

public class Kyogre extends Engimon {
    private void InitComp() {
        namaSpecies = "Kyogre";
        monElements[0].setElementType(Water);
        monElements[1].setElementType(Ice);
        monSkills[0] = new Torrent();
        monSkills[1] = new IceVortex();
        imageSource = "assets/kyogre.png";
    }

    public Kyogre() {
        super();
        InitComp();
    }

    public Kyogre(String name) {
        super(name);
        InitComp();
    }
}
