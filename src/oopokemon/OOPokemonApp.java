package oopokemon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import oopokemon.inventory.InventoryGUI;
import oopokemon.inventory.InventroyItem;
import oopokemon.map.Map;

import oopokemon.misc.*;
import oopokemon.occupier.EnemyHandler;
import oopokemon.occupier.Player;
import oopokemon.exception.NotInitializedException;
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
    private ImageView healthbar;
    private InventroyItem pauseButton = new InventroyItem(100,100);
    private GameState gameState;
    private Map isekai;
    private Player myPlayer;

    private Parent newGame() {
        initPane();
        loadConfig();
        setUpCamera();
        playBGM();

        gameState = new GameState(mapContainer);
        return initGameComponents();
    }

    private Parent loadGemu() {
        initPane();
        loadConfig();
        setUpCamera();
        playBGM();
        try {
            gameState = loadGame("testing", mapContainer);
        } catch (NotInitializedException e) {
            AlertBox.display("Load Game", "Gagal Load Game, Memulai Game Baru!");
            gameState = new GameState(mapContainer);
        }
        return initGameComponents();
    }

    private Parent initGameComponents() {
        isekai = gameState.map;
        myPlayer = gameState.player;
        enemyHandler = gameState.enemyhandler;
        healthbar = myPlayer.getHealthbar();
//        pauseButton.setImage(new Image("assets/pause.png"));
        pauseButton.setTranslateX(getCameraWidth()-120);
        pauseButton.setTranslateY(20);

        renderer = new Renderer(isekai);
        renderer.render(mapContainer);
        camera.getChildren().addAll(healthbar, pauseButton);
        cameraHandler();
        return root;
    }

    private void playBGM() {
        musicPlayer = new MusicPlayer("bin/music/Anville Town.mp3", MusicPlayer.MusicType.BGM,true);
        musicPlayer.play();
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

    private void setUpCamera() {
        camera.setMinSize(getCameraWidth(), getCameraHeight());
        camera.setPrefSize(getCameraWidth(), getCameraHeight());
        camera.setMaxSize(getCameraWidth(), getCameraHeight());
        camera.getChildren().add(mapContainer);
    }

    private void cameraHandler() {
        mapContainer.setTranslateX(-getCameraWidth() * (myPlayer.position.x / getNumOfCellHoriz()));
        mapContainer.setTranslateY(-getCameraHeight() * (myPlayer.position.y / getNumOfCellVert()));
    }

    private enum GameModeType {
        NewGame,
        LoadGame
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("OOPokemon");
        stage.setResizable(false);

        stage.getIcons().add(new Image("assets/oopokemon.png"));
        setGameToMainMenu(stage);
        stage.show();
    }

    private void setGameToMainGame(Stage stage, GameModeType mode) {
        Scene mainGame;
        if (enemyHandler != null){
            enemyHandler.interrupt();
        }
        if (mode == GameModeType.LoadGame) {
            mainGame = new Scene(loadGemu());
        }
        else {
            mainGame = new Scene(newGame());
        }
        mainGame.getStylesheets().add("assets/styles.css");
//        mapContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
//        camera.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        mainGame.setFill(Color.BLACK);
//        enemyHandler.sizeProperty.addListener((observable, oldValue, newValue) -> {
//
//        });

        mainGame.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A:
                case LEFT:
                    myPlayer.setPositionOcc(myPlayer.position.x - 1, myPlayer.position.y);
                    break;
                case D:
                case RIGHT:
                    myPlayer.setPositionOcc(myPlayer.position.x + 1, myPlayer.position.y);
                    break;
                case W:
                case UP:
                    myPlayer.setPositionOcc(myPlayer.position.x, myPlayer.position.y - 1);
                    break;
                case S:
                case DOWN:
                    myPlayer.setPositionOcc(myPlayer.position.x, myPlayer.position.y + 1);
                    break;
                case SPACE:
                    enemyHandler.suspend();
                    myPlayer = Battle.battle(myPlayer, enemyHandler);
                    enemyHandler.resume();
                    break;
                case B:
                    enemyHandler.suspend();
                    myPlayer.openInventory();
                    enemyHandler.resume();
                    break;
                case I:
                    myPlayer.interacts();
                    break;
                case ESCAPE:
                    setGameToPause(stage, mainGame);
                    break;
            }
            cameraHandler();

        });

        Button pbtn = pauseButton.getButton();
        pbtn.setOnAction(event -> {
            setGameToPause(stage, mainGame);
        });

        if (myPlayer == null){
            musicPlayer.interrupt();
            AlertBox.display("GAME OVER", "ANDA KALAH");
            setGameToMainMenu(stage);
        }
        stage.setScene(mainGame);
    }

    private void setGameToPause(Stage stage, Scene previouseScene) {
        enemyHandler.suspend();
        musicPlayer.pause();
        Pane pane = new Pane();
        Scene pauseScene = new Scene(pane, previouseScene.getWidth(), previouseScene.getHeight());
        pauseScene.getStylesheets().add("assets/styles.css");
        VBox uiContainer = new VBox(10);
        pane.setId("scene");
        int btnWidth = 175;
        int btnHeight = 50;

        // Membuat Tombol
        Button btn_resume = new Button("Resume");
        btn_resume.setPrefSize(btnWidth, btnHeight);
        btn_resume.setId("button");
        Button btn_save = new Button("Save");
        btn_save.setPrefSize(btnWidth, btnHeight);
        btn_save.setId("button");
        Button btn_load = new Button("Load");
        btn_load.setPrefSize(btnWidth, btnHeight);
        btn_load.setId("button");
        Button btn_help = new Button("Help");
        btn_help.setPrefSize(btnWidth, btnHeight);
        btn_help.setId("button");
        Button btn_main = new Button("Main Menu");
        btn_main.setPrefSize(btnWidth, btnHeight);
        btn_main.setId("button");

        // Setup layout
        uiContainer.getChildren().addAll(btn_resume, btn_save, btn_load, btn_help, btn_main);
        pane.getChildren().add(uiContainer);
        uiContainer.setLayoutX((previouseScene.getWidth() - btnWidth)/2);
        uiContainer.setLayoutY((previouseScene.getHeight() - 5*(btnHeight+10))/2);

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
            enemyHandler.interrupt();
            try {
                gameState = loadGame("testing", mapContainer);
            } catch (NotInitializedException e){
                System.err.println(e.getErrorMessage());

            }
            isekai = gameState.map;
            myPlayer = gameState.player;
            enemyHandler = gameState.enemyhandler;
            camera.getChildren().remove(healthbar);
            healthbar = myPlayer.getHealthbar();
            camera.getChildren().add(healthbar);
            renderer = new Renderer(gameState.map);
            renderer.render(mapContainer);
            cameraHandler();
            playBGM();
            stage.setScene(previouseScene);
        });

        btn_help.setOnAction(event -> {
            setGameToHelp(stage, pauseScene);
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


    private void setGameToMainMenu(Stage stage) {
        musicPlayer = null;
        stage.centerOnScreen();
        if (enemyHandler != null){
            enemyHandler.interrupt();
        }
        loadConfig();
        Pane mainMenuContainer = new Pane();
        Scene mainMenu = new Scene(mainMenuContainer);
        mainMenu.getStylesheets().add("assets/styles.css");
        mainMenuContainer.setPrefSize(getCameraWidth(), getCameraHeight());
//        ImageView backgroundImage = new ImageView(new Image("assets/pokemon.jpg"));
//        backgroundImage.fitWidthProperty().bind(mainMenu.widthProperty());
//        backgroundImage.setPreserveRatio(true);
//        mainMenuContainer.getChildren().add(backgroundImage);
        mainMenuContainer.setId("scene");
        VBox uiContainer = new VBox(10);

        int btnWidth = 175;
        int btnHeight = 50;

        // Membuat Tombol
        Button btn_new = new Button("New Game");
        btn_new.setPrefSize(btnWidth, btnHeight);
        btn_new.setId("button");
        Button btn_load = new Button("Load");
        btn_load.setPrefSize(btnWidth, btnHeight);
        btn_load.setId("button");
        Button btn_help = new Button("Help");
        btn_help.setPrefSize(btnWidth, btnHeight);
        btn_help.setId("button");
        Button btn_setting = new Button("Settings");
        btn_setting.setPrefSize(btnWidth, btnHeight);
        btn_setting.setId("button");


        // Setup layout
        uiContainer.getChildren().addAll(btn_new,btn_load,btn_help,btn_setting);
        mainMenuContainer.getChildren().add(uiContainer);
        uiContainer.setTranslateX((getCameraWidth() - btnWidth)/2);
        uiContainer.setTranslateY((getCameraHeight() - 4*(btnHeight+10))/2);

        // Setup Tombol
        btn_new.setOnAction(event -> setGameToMainGame(stage, GameModeType.NewGame));

        btn_load.setOnAction(event -> {
            setGameToMainGame(stage, GameModeType.LoadGame);
        });

        btn_help.setOnAction(event -> {
            setGameToHelp(stage, mainMenu);
//            InventoryGUI.createInventory(null);
        });

        btn_setting.setOnAction(event -> {
            setGametoSetting(stage);
        });

        stage.setScene(mainMenu);

        stage.show();

    }
    private void setGameToHelp(Stage stage, Scene previousScene){
        Pane helpContainer = new Pane();
        Scene helpScreen = new Scene(helpContainer);

        VBox uiContainer = new VBox(10);
        uiContainer.setAlignment(Pos.CENTER);
        helpContainer.setPrefSize(getCameraWidth(), getCameraHeight());
        helpScreen.getStylesheets().add("assets/styles.css");
        helpContainer.setId("scene");
        helpContainer.getChildren().add(uiContainer);


        Label lbl_move = new Label("Bergerak: W/A/S/D or ↑/←/↓/→");
        lbl_move.setId("label");
        Label lbl_battle = new Label("Mengajukan Battle: SPACE");
        lbl_battle.setId("label");
        Label lbl_inventory = new Label("Inventory: B");
        lbl_inventory.setId("label");
        Label lbl_interaction = new Label("Interaction: I");
        lbl_interaction.setId("label");
        Label lbl_pause = new Label("Pause: Esc");
        lbl_pause.setId("label");
        Button btn_back = new Button("Back");
        btn_back.setPrefSize(175, 50);
        btn_back.setId("button");
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);

        uiContainer.getChildren().addAll(lbl_move, lbl_battle, lbl_inventory, lbl_interaction, lbl_pause, btn_back, imageView);

        uiContainer.setTranslateX((getCameraWidth() - 300)/2);
        uiContainer.setTranslateY((getCameraHeight() - 5*(10 + 20))/2);

        btn_back.setOnAction(event -> {
            stage.setScene(previousScene);
        });

        helpScreen.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) stage.setScene(previousScene);
        });

        stage.setScene(helpScreen);
    }


    private void setGametoSetting(Stage stage){
        Pane settingContainer = new Pane();
        Scene settingScreen = new Scene(settingContainer);

        settingContainer.setPrefSize(getCameraWidth(), getCameraHeight());
        settingScreen.getStylesheets().add("assets/styles.css");
        settingContainer.setId("scene");
        VBox uiContainer = new VBox(10);

        int btnWidth = 175;
        int btnHeight = 50;

        int sliderWidth = 500;

//        // Setup UI
//        Label lbl_cellSize = new Label("Tile size (pixels):");
//        lbl_cellSize.setPrefSize(btnWidth, btnHeight);

        Label lbl_musicVol = new Label("Music Volume");
        lbl_musicVol.setPrefSize(sliderWidth, btnHeight);
        lbl_musicVol.setId("label");
        lbl_musicVol.setPadding(new Insets(20,0,0,0));

        Label lbl_sfxVol = new Label("SFX Volume");
        lbl_sfxVol.setPrefSize(sliderWidth, btnHeight);
        lbl_sfxVol.setId("label");
        lbl_sfxVol.setPadding(new Insets(20,0,0,0));


        Label lbl_horizCell = new Label("Number of Horizonal Tiles");
        lbl_horizCell.setPrefSize(sliderWidth, btnHeight);
        lbl_horizCell.setId("label");
        lbl_horizCell.setPadding(new Insets(20,0,0,0));


        Label lbl_vertCell = new Label("Number of Vertical Tiles");
        lbl_vertCell.setPrefSize(sliderWidth, btnHeight);
        lbl_vertCell.setId("label");
        lbl_vertCell.setPadding(new Insets(20,0,0,0));


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
//        btn_save.setPadding(new Insets(10,0,0,0));
        btn_save.setTranslateY(20);
        btn_save.setTranslateX((sliderWidth-btnWidth)/2);
        btn_save.setId("button");

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
            setGameToMainMenu(stage);
        });

        stage.setScene(settingScreen);

        settingScreen.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) setGameToMainMenu(stage);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (this.musicPlayer != null){
            musicPlayer.interrupt();
        }
        if (this.enemyHandler != null){
            enemyHandler.interrupt();
        }
    }
}
