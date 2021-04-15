package OOPokemon.Species;

import OOPokemon.Skill.Torrent;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.Water;

public class Squirtle extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Squirtle";
        monElements[0].setElementType(Water);
        this.monSkills[0] = new Torrent();
        image = new Image("assets/squirtle.png");
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
