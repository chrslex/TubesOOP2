package OOPokemon;
import OOPokemon.Map.Map;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OOPokemonApp extends Application {

    private Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(root));
        stage.setTitle("OOPokemon");
        new Map("src/OOPokemon/Map/map1.txt", root);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
