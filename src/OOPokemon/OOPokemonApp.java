package OOPokemon;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OOPokemonApp extends Application {

    private Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(root, 600, 800));
        stage.setTitle("OOPokemon");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
