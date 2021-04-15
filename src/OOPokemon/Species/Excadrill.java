package OOPokemon.Species;

import static OOPokemon.Element.ElementType.Ground;

public class Excadrill extends Engimon {
    private void InitComp() {
        this.namaSpecies = "Excadrill";
        monElements[0].setElementType(Ground);
//        this.monSkills[0] = IceVortex();
    }

    public Excadrill() {
        super();
        InitComp();
    }

    public Excadrill(String name) {
        super(name);
        InitComp();
    }
}
