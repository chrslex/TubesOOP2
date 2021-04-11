package OOPokemon;
import OOPokemon.Map.Map;

import OOPokemon.Occupier.Player;
import OOPokemon.misc.Sprite;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OOPokemonApp extends Application {

    private BorderPane root = new BorderPane();
    private Pane mapPlaceHolder = new Pane();
    private Pane guiPlaceHolder = new Pane();
    private Map isekai = new Map("src/OOPokemon/Map/map1.txt", mapPlaceHolder);
    private Player myPlayer = new Player(3, 3, mapPlaceHolder, isekai);

    private Parent initComp() {
        root.setMaxSize(1920, 1080);
        root.setTop(mapPlaceHolder);
        root.setBottom(guiPlaceHolder);

        Sprite engimonSprite = new Sprite();
        engimonSprite.setLayoutX(500);
        engimonSprite.setLayoutY(400);
        engimonSprite.setCenterImage(new Image("assets/raichu.png"));
        engimonSprite.setBottomRightImage(new Image("assets/electric.png"));
        mapPlaceHolder.getChildren().add(engimonSprite);


        Button buttonKiri =  new Button("Gerak Kiri");
        buttonKiri.setOnAction((e) -> myPlayer.setPositionOcc(myPlayer.position.x - 1, myPlayer.position.y));
        buttonKiri.setLayoutX(0);
        buttonKiri.setLayoutY(0);
        Button buttonKanan = new Button("Gerak Kanan");
        buttonKanan.setOnAction((e) -> myPlayer.setPositionOcc(myPlayer.position.x + 1, myPlayer.position.y));
        buttonKanan.setLayoutY(50);
        guiPlaceHolder.getChildren().addAll(buttonKiri,buttonKanan);


        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene mainGame = new Scene(initComp());

        mainGame.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A:
                    myPlayer.setPositionOcc(myPlayer.position.x - 1, myPlayer.position.y);
                    break;
                case D:
                    myPlayer.setPositionOcc(myPlayer.position.x + 1, myPlayer.position.y);
                    break;
                case W:
                    myPlayer.setPositionOcc(myPlayer.position.x, myPlayer.position.y - 1);
                    break;
                case S:
                    myPlayer.setPositionOcc(myPlayer.position.x, myPlayer.position.y + 1);
                case SPACE:
                    break;
            }
        });

        stage.setScene(mainGame);
        stage.setTitle("OOPokemon");
        stage.getIcons().add(new Image("assets/oopokemon.png"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
