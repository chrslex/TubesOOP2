package OOPokemon.misc;

import static OOPokemon.misc.GameState.*;

public class Config{
    public final float cellWidth = getCellWidth();
    public final float cellHeight = getCellHeight();
    public final int numOfCellHoriz = getNumOfCellHoriz();
    public final int numOfCellVert = getNumOfCellVert();
    public final double musicVol = getMusicVol();
    public final double sfxVol = getSfxVol();
    Config(){}
}