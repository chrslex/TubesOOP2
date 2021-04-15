package OOPokemon.Species;

import OOPokemon.Skill.IceVortex;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.Ice;

public class Articuno extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Articuno";
        monElements[0].setElementType(Ice);
        monSkills[0] = new IceVortex();
        image = new Image("assets/articuno.png");

    }

    public Articuno() {
        super();
        InitComp();
    }

    public Articuno(String name) {
        super(name);
        InitComp();
    }
}
