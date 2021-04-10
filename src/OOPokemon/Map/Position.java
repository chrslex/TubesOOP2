package OOPokemon.Map;

public class Position {
    public int x;
    public int y;

    public static int MAX_X = 30;
    public static int MAX_Y = 20;

    public Position() {
        x = 0;
        y = 0;
    }

    public Position(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public boolean setPosition(int _x, int _y)
    {
        if (isValidCoordinate(_x, _y)){
            x = _x;
            y = _y;
            return true;
        }
        return false;
    }

    public static boolean isValidCoordinate(int x, int y){
        return 0 <= x && x < MAX_X && 0 <= y && y < MAX_Y;
    }
}
