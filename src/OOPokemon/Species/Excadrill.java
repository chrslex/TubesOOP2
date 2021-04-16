package OOPokemon.Species;

import OOPokemon.Skill.Fissure;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.Ground;

public class Excadrill extends Engimon {
    private void InitComp() {
        namaSpecies = "Excadrill";
        monElements[0].setElementType(Ground);
        monSkills[0] = new Fissure();
        imageSource = "assets/excadrill.png";

    }

    public Excadrill() {
        super();
        InitComp();
    }

    public Excadrill(String name) {
        super(name);
        InitComp();
    }
}
