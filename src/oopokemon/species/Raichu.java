package oopokemon.species;

import oopokemon.skill.StaticStorm;
import javafx.scene.image.Image;

import static oopokemon.element.ElementType.Electric;

public class Raichu extends Engimon {
    private void InitComp() {
        namaSpecies = "Raichu";
        monElements[0].setElementType(Electric);
        monSkills[0] = new StaticStorm();
        imageSource = "assets/raichu.png";
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
