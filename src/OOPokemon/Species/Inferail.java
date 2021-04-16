package OOPokemon.Species;

import OOPokemon.Skill.StaticStorm;
import OOPokemon.Skill.Sunstrike;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.*;

public class Inferail extends Engimon {
    private void InitComp() {
        namaSpecies = "Inferail";
        monElements[0].setElementType(Fire);
        monElements[1].setElementType(Electric);
        monSkills[0] = new Sunstrike();
        monSkills[1] = new StaticStorm();
        imageSource = "assets/inferail.png";

    }

    public Inferail() {
        super();
        InitComp();
    }

    public Inferail(String name) {
        super(name);
        InitComp();
    }
}
