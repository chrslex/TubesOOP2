package OOPokemon.Map;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;



public abstract class Occupier extends Rectangle {
    public OccupierType occupierType;


    Occupier() {
        super(40,40, Color.BLACK);
        setTranslateX(0);
        setTranslateY(0);
    }



}
