package OOPokemon.Species;

import OOPokemon.Skill.Fissure;
import OOPokemon.Skill.IceVortex;
import OOPokemon.Skill.Torrent;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.*;

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
