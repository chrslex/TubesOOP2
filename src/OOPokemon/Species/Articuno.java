package OOPokemon.Species;

import static OOPokemon.Element.ElementType.Ice;

public class Articuno extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Articuno";
        monElements[0].setElementType(Ice);
//        this.monSkills[0] = IceVortex();
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
