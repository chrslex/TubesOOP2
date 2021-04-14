package OOPokemon;
import OOPokemon.Map.Map;

import OOPokemon.Occupier.Player;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static OOPokemon.GameState.*;

public class OOPokemonApp extends Application {

    private BorderPane root;
    private Pane camera;

    private Map isekai;
    private Player myPlayer;

    private Parent newGame() {
        root = new BorderPane();
        camera = new Pane();

        loadConfig();
        setUpCamera();

        Pane mapPlaceHolder = new Pane();
        Pane guiPlaceHolder = new Pane();

        GameState gameState = new GameState(mapPlaceHolder);

        isekai = gameState.map;
        myPlayer = gameState.player;
        root.setTop(guiPlaceHolder);
        root.setBottom(camera);
        guiPlaceHolder.toFront();
        camera.getChildren().addAll(mapPlaceHolder);
        cameraHandler();
        return root;
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("OOPokemon");
        stage.getIcons().add(new Image("assets/oopokemon.png"));
        createMainMenu(stage);
        stage.show();
    }

    private void setGameToMainGame(Stage stage, GameModeType mode) {
        Scene mainGame;

        if (mode == GameModeType.LoadGame) {
            System.out.println("test1");

            mainGame = new Scene(newGame());
        }
        else {
            System.out.println("test2");
            mainGame = new Scene(newGame());
        }

        System.out.println("test3");

        mainGame.setFill(Color.BLACK);

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
                    // BATTLE
                    break;
                case I:
                    // Buka Inventory
                    break;
                case ESCAPE:
                    setGameToPause(stage, mainGame);
            }
            cameraHandler();
            System.out.println(event);
        });


        stage.setScene(mainGame);
    }

    private void setGameToPause(Stage stage, Scene scene) {
        Pane pane = new Pane();
        Scene pauseScene = new Scene(pane, scene.getWidth(), scene.getHeight());
        VBox uiContainer = new VBox(10);

        int btnWidth = 175;
        int btnHeight = 50;

        // Membuat Tombol
        Button btn_resume = new Button("Resume");
        btn_resume.setPrefSize(btnWidth, btnHeight);

        Button btn_save = new Button("Save");
        btn_save.setPrefSize(btnWidth, btnHeight);

        Button btn_load = new Button("Load");
        btn_load.setPrefSize(btnWidth, btnHeight);

        Button btn_main = new Button("Main Menu");
        btn_main.setPrefSize(btnWidth, btnHeight);


        // Setup layout
        uiContainer.getChildren().addAll(btn_resume,btn_save,btn_load, btn_main);
        pane.getChildren().add(uiContainer);
        uiContainer.setLayoutX((scene.getWidth() - btnWidth)/2);
        uiContainer.setLayoutY((scene.getHeight() - 4*(btnHeight+10))/2);

        // Setup Tombol
        btn_resume.setOnAction(event -> stage.setScene(scene));

        btn_save.setOnAction(event -> {

        });

        btn_load.setOnAction(event -> {

        });

        btn_main.setOnAction(event -> createMainMenu(stage));

        stage.setScene(pauseScene);

        pauseScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(scene);
            }
        });

        stage.show();
    }


    private void createMainMenu(Stage stage) {
        loadConfig();
        Pane mainMenuContainer = new Pane();
        Scene mainMenu = new Scene(mainMenuContainer);

        mainMenuContainer.setPrefSize(getCameraWidth(), getCameraHeight());

        VBox uiContainer = new VBox(10);

        int btnWidth = 175;
        int btnHeight = 50;

        // Membuat Tombol
        Button btn_new = new Button("New Game");
        btn_new.setPrefSize(btnWidth, btnHeight);
        Button btn_load = new Button("Load");
        btn_load.setPrefSize(btnWidth, btnHeight);
        Button btn_hof = new Button("Hall of Fame");
        btn_hof.setPrefSize(btnWidth, btnHeight);
        Button btn_setting = new Button("Setting");
        btn_setting.setPrefSize(btnWidth, btnHeight);


        // Setup layout
        uiContainer.getChildren().addAll(btn_new,btn_load,btn_hof,btn_setting);
        mainMenuContainer.getChildren().add(uiContainer);
        uiContainer.setTranslateX((getCameraWidth() - btnWidth)/2);
        uiContainer.setTranslateY((getCameraHeight() - 4*(btnHeight+10))/2);

        // Setup Tombol
        btn_new.setOnAction(event -> setGameToMainGame(stage, GameModeType.NewGame));

        btn_load.setOnAction(event -> setGameToMainGame(stage, GameModeType.LoadGame));

        btn_hof.setOnAction(event -> {
            // work on progress
        });

        btn_setting.setOnAction(event -> {
            // work on progress
        });

        stage.setScene(mainMenu);


        stage.show();

    }

    private void setUpCamera() {
        camera.setMinSize(getCameraWidth(), getCameraHeight());
        camera.setPrefSize(getCameraWidth(), getCameraHeight());
        camera.setMaxSize(getCameraWidth(), getCameraHeight());
    }

    private void cameraHandler() {
        camera.setTranslateX(-getCameraWidth() * (myPlayer.position.x / getNumOfCellHoriz()));
        camera.setTranslateY(-getCameraHeight() * (myPlayer.position.y / getNumOfCellVert()));
    }

    private enum GameModeType {
        NewGame,
        LoadGame
    }

    public static void main(String[] args) {
        launch(args);
    }
}
