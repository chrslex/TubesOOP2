package oopokemon;
import oopokemon.map.Map;

import oopokemon.occupier.EnemyHandler;
import oopokemon.occupier.Player;
import oopokemon.exception.NotInitializedException;
import oopokemon.misc.GameState;
import oopokemon.misc.MusicPlayer;
import oopokemon.misc.Renderer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;


import javafx.scene.paint.Color;
import javafx.stage.Stage;


import static oopokemon.misc.GameState.*;

public class OOPokemonApp extends Application {

    private BorderPane root;
    private Pane camera;
    private Pane mapContainer;
    private Pane guiContainer;
    private MusicPlayer musicPlayer;

    private EnemyHandler enemyHandler;
    private Renderer renderer;

    private GameState gameState;
    private Map isekai;
    private Player myPlayer;

    private Parent newGame() {
        initPane();
        loadConfig();
        setUpCamera();
        playBGM();
        gameState = new GameState();
        isekai = gameState.map;
        myPlayer = gameState.player;
        enemyHandler = gameState.enemyhandler;

        renderer = new Renderer(isekai);
        renderer.render(mapContainer);

        cameraHandler();
        return root;
    }

    private Parent loadGemu() {
        initPane();
        loadConfig();
        setUpCamera();
        playBGM();
        try {
            gameState = loadGame("testing");
        } catch (NotInitializedException e) {
            System.err.println("gagal menload game memuat game baru");
            gameState = new GameState();
        }
        isekai = gameState.map;
        myPlayer = gameState.player;
        enemyHandler = gameState.enemyhandler;

        renderer = new Renderer(isekai);
        renderer.render(mapContainer);

        cameraHandler();
        return root;
    }

    private void playBGM() {
        musicPlayer = new MusicPlayer("bin/music/Anville Town.mp3", MusicPlayer.MusicType.BGM,true);
        musicPlayer.run();
    }

    private void initPane() {
        root = new BorderPane();
        camera = new Pane();
        mapContainer = new Pane();
        guiContainer = new Pane();
        guiContainer.toFront();
        root.setTop(guiContainer);
        root.setBottom(camera);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("oopokemon");
        stage.getIcons().add(new Image("assets/oopokemon.png"));
        setGameToMainMenu(stage);
        stage.show();
    }

    private void setGameToMainGame(Stage stage, GameModeType mode) {
        Scene mainGame;

        if (mode == GameModeType.LoadGame) {
            mainGame = new Scene(loadGemu());
        }
        else {
            mainGame = new Scene(newGame());
        }


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
                    break;
                case SPACE:
                    enemyHandler.suspend();
                    break;
                case I:
                    enemyHandler.resume();
                    break;
                case L:
                    renderer.unRender(mapContainer);
                    break;
                case K:
                    renderer.render(mapContainer);
                    break;
                case ESCAPE:
                    setGameToPause(stage, mainGame);
                    break;
            }
            cameraHandler();

        });


        stage.setScene(mainGame);
    }

    private void setGameToPause(Stage stage, Scene previouseScene) {
        enemyHandler.suspend();
        musicPlayer.pause();
        Pane pane = new Pane();
        Scene pauseScene = new Scene(pane, previouseScene.getWidth(), previouseScene.getHeight());
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
        uiContainer.setLayoutX((previouseScene.getWidth() - btnWidth)/2);
        uiContainer.setLayoutY((previouseScene.getHeight() - 4*(btnHeight+10))/2);

        // Setup Tombol
        btn_resume.setOnAction(event -> {
            enemyHandler.resume();
            musicPlayer.play();
            stage.setScene(previouseScene);
        });

        btn_save.setOnAction(event -> {
            gameState.saveGame("testing");
        });

        btn_load.setOnAction(event -> {
            renderer.unRender(mapContainer);
            try {
                gameState = GameState.loadGame("testing");
            } catch (NotInitializedException e){
                System.err.println(e.getErrorMessage());

            }
            isekai = gameState.map;
            myPlayer = gameState.player;
            renderer = new Renderer(gameState.map);
            renderer.render(mapContainer);
            cameraHandler();
            stage.setScene(previouseScene);
        });

        btn_main.setOnAction(event -> {
            musicPlayer.interrupt();
            musicPlayer = null;
            setGameToMainMenu(stage);
        });

        stage.setScene(pauseScene);

        pauseScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                enemyHandler.resume();
                musicPlayer.play();
                stage.setScene(previouseScene);
            }
        });

        stage.show();
    }

    private void escapeToReturn(Stage stage,  Scene currentScene, Scene destScene) {
        currentScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                musicPlayer.play();
                stage.setScene(destScene);
            }
        });
    }


    private void setGameToMainMenu(Stage stage) {
        musicPlayer = null;
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

        btn_load.setOnAction(event -> {
            setGameToMainGame(stage, GameModeType.LoadGame);
        });

        btn_hof.setOnAction(event -> {
            // work on progress
        });

        btn_setting.setOnAction(event -> {
            setGametoSetting(stage);
        });

        stage.setScene(mainMenu);


        stage.show();

    }
    private void setGametoSetting(Stage stage){
        Pane settingContainer = new Pane();
        Scene settingScreen = new Scene(settingContainer);

        settingContainer.setPrefSize(getCameraWidth(), getCameraHeight());

        VBox uiContainer = new VBox(10);

        int btnWidth = 175;
        int btnHeight = 50;

        int sliderWidth = 500;

//        // Setup UI
//        Label lbl_cellSize = new Label("Tile size (pixels):");
//        lbl_cellSize.setPrefSize(btnWidth, btnHeight);

        Label lbl_musicVol = new Label("Music Volume");
        lbl_musicVol.setPrefSize(btnWidth, btnHeight);

        Label lbl_sfxVol = new Label("SFX Volume");
        lbl_sfxVol.setPrefSize(btnWidth, btnHeight);

        Label lbl_horizCell = new Label("Number of Horizonal Tiles");
        lbl_horizCell.setPrefSize(btnWidth, btnHeight);

        Label lbl_vertCell = new Label("Number of Vertical Tiles");
        lbl_vertCell.setPrefSize(btnWidth, btnHeight);


//        Slider slider_cellsize = new Slider(10, 200, getCellWidth());
//        slider_cellsize.setMinWidth(sliderWidth/2);
//        slider_cellsize.setBlockIncrement(10);
//        slider_cellsize.setMajorTickUnit(10);
//        slider_cellsize.setMinorTickCount(0);
//        slider_cellsize.setShowTickLabels(true);
//        slider_cellsize.setSnapToTicks(true);


        Slider slider_hcell = new Slider(1, 20, getNumOfCellHoriz());
        slider_hcell.setMinWidth(sliderWidth);
        slider_hcell.setBlockIncrement(1);
        slider_hcell.setMajorTickUnit(1);
        slider_hcell.setMinorTickCount(0);
        slider_hcell.setShowTickLabels(true);
        slider_hcell.setSnapToTicks(true);

        Slider slider_vcell = new Slider(1, 20, getNumOfCellVert());
        slider_vcell.setMinWidth(sliderWidth);
        slider_vcell.setBlockIncrement(1);
        slider_vcell.setMajorTickUnit(1);
        slider_vcell.setMinorTickCount(0);
        slider_vcell.setShowTickLabels(true);
        slider_vcell.setSnapToTicks(true);

        Slider slider_music_vol = new Slider(0, 1, getMusicVol());
//        slider_music_vol.setShowTickMarks(true);
//        slider_music_vol.setShowTickLabels(true);
        slider_music_vol.setBlockIncrement(0.1);

        Slider slider_sfx_vol = new Slider(0, 1, getSfxVol());
//        slider_sfx_vol.setShowTickMarks(true);
//        slider_sfx_vol.setShowTickLabels(true);
        slider_sfx_vol.setBlockIncrement(0.1);

        Button btn_save = new Button("Save");
        btn_save.setPrefSize(btnWidth, btnHeight);
        btn_save.setTranslateX((sliderWidth-btnWidth)/2);


        // Setup layout
        uiContainer.getChildren().addAll(
                lbl_horizCell, slider_hcell,
                lbl_vertCell, slider_vcell,
                lbl_musicVol, slider_music_vol,
                lbl_sfxVol, slider_sfx_vol,
                btn_save);

        settingContainer.getChildren().add(uiContainer);
        uiContainer.setTranslateX((getCameraWidth() - sliderWidth)/2);
        uiContainer.setTranslateY((getCameraHeight() - 8.5*(btnHeight+10))/2);

        // Setup Tombol
        btn_save.setOnAction(event -> {
            GameState.saveConfig(
                    (int) slider_hcell.getValue(),
                    (int) slider_vcell.getValue(),
                    slider_music_vol.getValue(),
                    slider_sfx_vol.getValue());
        });


        stage.setScene(settingScreen);

        settingScreen.setOnKeyPressed(event -> {
            setGameToMainMenu(stage);
        });

        stage.show();
    }

    private void setUpCamera() {
        camera.setMinSize(getCameraWidth(), getCameraHeight());
        camera.setPrefSize(getCameraWidth(), getCameraHeight());
        camera.setMaxSize(getCameraWidth(), getCameraHeight());
        camera.getChildren().add(mapContainer);
    }

    private void cameraHandler() {
        System.out.println(myPlayer.position.x + ", "+  myPlayer.position.y);
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
