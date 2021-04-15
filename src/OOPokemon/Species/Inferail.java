package OOPokemon.Species;

import static OOPokemon.Element.ElementType.*;

public class Inferail extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Inferail";
        monElements[0].setElementType(Fire);
        monElements[1].setElementType(Electric);
//        this.monSkills[0] = IceVortex();
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
