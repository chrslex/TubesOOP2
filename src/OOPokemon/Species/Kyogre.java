package OOPokemon.Species;

import static OOPokemon.Element.ElementType.*;

public class Kyogre extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Kyogre";
        monElements[0].setElementType(Water);
        monElements[1].setElementType(Ice);
//        this.monSkills[0] = IceVortex();
    }

    public Kyogre() {
        super();
        InitComp();
    }

    public Kyogre(String name) {
        super(name);
        InitComp();
    }
}
