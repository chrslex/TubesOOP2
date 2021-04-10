package OOPokemon;
import OOPokemon.Map.Map;

import OOPokemon.Occupier.Player;
import OOPokemon.misc.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

public class OOPokemonApp extends Application {

    private Pane root = new Pane();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(root));
        stage.setTitle("OOPokemon");
        stage.getIcons().add(new Image("assets/oopokemon.png"));

        root.setMaxSize(1920, 1080);

        new Map("src/OOPokemon/Map/map1.txt", root);

        new Player(3, 3, root);


        Sprite engimonSprite = new Sprite();
        engimonSprite.setLayoutX(500);
        engimonSprite.setLayoutY(400);
        engimonSprite.setCenterImage(new Image("assets/raichu.png"));
        engimonSprite.setBottomRightImage(new Image("assets/electric.png"));


        root.getChildren().addAll(engimonSprite);

        stage.setResizable(false);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
