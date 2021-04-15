package OOPokemon.Species;

import OOPokemon.Skill.StaticStorm;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.Electric;

public class Raichu extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Raichu";
        monElements[0].setElementType(Electric);
        this.monSkills[0] = new StaticStorm();
        image = new Image("assets/raichu.png");
    }

    public Raichu() {
        super();
        InitComp();
    }

    public Raichu(String name) {
        super(name);
        InitComp();
    }

    @Override
    public Image getElementImage() {
        return super.getElementImage();
    }
}
