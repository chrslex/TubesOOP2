package OOPokemon.Species;

import static OOPokemon.Element.ElementType.Water;

public class Squirtle extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Squirtle";
        monElements[0].setElementType(Water);
//        this.monSkills[0] = IceVortex();
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
