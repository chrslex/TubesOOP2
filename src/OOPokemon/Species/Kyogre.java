package OOPokemon.Species;

import OOPokemon.Skill.IceVortex;
import OOPokemon.Skill.Torrent;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.*;

public class Kyogre extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Kyogre";
        monElements[0].setElementType(Water);
        monElements[1].setElementType(Ice);
        monSkills[0] = new Torrent();
        monSkills[1] = new IceVortex();
        image = new Image("assets/kyogre.png");
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
