package OOPokemon.Species;

import OOPokemon.Skill.Torrent;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.Water;

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
}
