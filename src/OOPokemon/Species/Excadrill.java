package OOPokemon.Species;

import OOPokemon.Skill.Fissure;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.Ground;

public class Excadrill extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Excadrill";
        monElements[0].setElementType(Ground);
        this.monSkills[0] = new Fissure();
        image = new Image("assets/excadrill.png");

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
