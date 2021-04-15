package OOPokemon.Species;

import static OOPokemon.Element.ElementType.Fire;

public class Dragon extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Dragon";
        monElements[0].setElementType(Fire);
//        this.monSkills[0] = IceVortex();
    }

    public Dragon() {
        super();
        InitComp();
    }

    public Dragon(String name) {
        super(name);
        InitComp();
    }
}
