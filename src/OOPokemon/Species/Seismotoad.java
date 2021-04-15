package OOPokemon.Species;

import static OOPokemon.Element.ElementType.*;

public class Seismotoad extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Seismotoad";
        monElements[0].setElementType(Water);
        monElements[1].setElementType(Ground);
//        this.monSkills[0] = IceVortex();
    }

    public Seismotoad() {
        super();
        InitComp();
    }

    public Seismotoad(String name) {
        super(name);
        InitComp();
    }
}
