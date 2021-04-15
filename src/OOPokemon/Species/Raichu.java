package OOPokemon.Species;

import static OOPokemon.Element.ElementType.Electric;

public class Raichu extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Raichu";
        monElements[0].setElementType(Electric);
//        this.monSkills[0] = IceVortex();
    }

    public Raichu() {
        super();
        InitComp();
    }

    public Raichu(String name) {
        super(name);
        InitComp();
    }
}
