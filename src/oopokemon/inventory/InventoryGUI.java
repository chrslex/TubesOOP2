package oopokemon.inventory;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oopokemon.misc.AlertBox;
import oopokemon.misc.InputBox;
import oopokemon.occupier.Player;
import oopokemon.skill.Skill;
import oopokemon.species.Engimon;

import java.util.List;

import static oopokemon.misc.InputBox.inputName;

public class InventoryGUI extends Pane {
    public enum InventoryType{
        ENGIMON,
        SKILL
    }

    public static void createInventory(Player player, InventoryType type){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene invScene = createInventoryScene(player, type, stage);
        stage.setScene(invScene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("assets/oopokemon.png"));
        stage.setTitle("Inventory");
        stage.showAndWait();
    }

    private static Scene createInventoryScene(Player player, InventoryType type, Stage stage) {
        StackPane inventMother = new StackPane();
        HBox inventoryLayout = new HBox();
        inventMother.getChildren().add(inventoryLayout);
        inventoryLayout.setAlignment(Pos.CENTER);
        Scene invScene = new Scene(inventMother);
//        invScene.setFill(Color.TRANSPARENT);
//        Platform.setImplicitExit(false);

        invScene.getStylesheets().add("assets/styles.css");
//        inventoryLayout.setId("scene");
        inventoryLayout.toBack();
        GridPane inventoryItems;
        if (type == InventoryType.ENGIMON){
            inventoryItems = populateItem(player.getInventory().listEngimon(), player, stage);
        }
        else {
            inventoryItems = populateItem(player.getInventory().listSkill(), player, stage);
        }
        ScrollPane invScrollItem = new ScrollPane(inventoryItems);
//        invScrollItem.setId("inventoryBackground");
        inventoryItems.toFront();
        invScrollItem.setFitToWidth(true);
        invScrollItem.setMaxHeight(650);
        Pane itemInfo = new Pane();
        inventoryItems.setPadding(new Insets(10,10,10,10));
        inventoryItems.setHgap(10);
        inventoryItems.setVgap(10);
        inventoryItems.setAlignment(Pos.CENTER);
        itemInfo.setPrefSize(300,600);

        HBox buttonPlaceHolder = new HBox(10);
        Button btnEngimon = new Button("ENGIMON");
        btnEngimon.setPrefWidth(130);
        btnEngimon.setId("button");
        Button btnSkill = new Button("SKILL");
        btnSkill.setPrefWidth(130);
        btnSkill.setId("button");
        buttonPlaceHolder.getChildren().addAll(btnEngimon,btnSkill);
        buttonPlaceHolder.setTranslateX((300-2*(10+130))/2);


        Button btnBreed = new Button("BREED");
        btnBreed.setPrefWidth(130);
        btnBreed.setId("button");
        btnBreed.setTranslateX(150-65);
        btnBreed.setTranslateY(300-20);

        btnEngimon.setOnAction(event -> {
            stage.setScene(createInventoryScene(player, InventoryType.ENGIMON, stage));
        });

        btnSkill.setOnAction(event -> {
            stage.setScene(createInventoryScene(player, InventoryType.SKILL, stage));
        });

        btnBreed.setOnAction(event -> {
            player.breeding();
            stage.setScene(createInventoryScene(player, InventoryType.ENGIMON, stage));
        });


        itemInfo.getChildren().addAll(buttonPlaceHolder, btnBreed);

        inventoryLayout.getChildren().addAll(invScrollItem, itemInfo);
        return invScene;
    }

    private static GridPane populateItem(List<? extends Item> items, Player player, Stage prevStage){
        GridPane gridPane = new GridPane();
        for (int i = 0; i < items.size(); i++) {
            InventroyItem inv = new InventroyItem(i, items.get(i), player, prevStage);
            gridPane.add(inv, i % 5, i / 5);
        }
        return gridPane;
    }

    public static void createInfo(Item item, int id, Player player, Stage prevStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox itemInfo = new VBox(10);
        itemInfo.setPadding(new Insets(10,25,15,25));
        itemInfo.setAlignment(Pos.CENTER);
//        itemInfo.setPrefSize(300, 600);;
        ImageView image = new ImageView(item.getImage());
        image.setFitWidth(150);
        image.setFitHeight(150);
        image.setPreserveRatio(true);
        Label idLabel = new Label("ID : "+id);
        idLabel.setId("label2");
        Label firstLine = new Label(item.getFirstLine());
        firstLine.setId("label2");
        Label secondLine = new Label(item.getSecondLine());
        secondLine.setId("label2");
        Label thirdLine = new Label(item.getThirdLine());
        thirdLine.setId("label2");
        Label fourthLine = new Label(item.getFourthLine());
        fourthLine.setId("label2");
        Label fifthLine;
        Button btnDummy = new Button();

        if (item instanceof Skill){
            int count = player.getInventory().getCountSkill((Skill)item);
            fifthLine = new Label("Jumlah : " + count);
        }
        else {
            fifthLine = new Label(item.getFifthLine());
        }
        fifthLine.setId("label2");

//        HBox buttonPlaceHolder = new HBox(10);
        Button useButton = new Button("Use");
        useButton.setId("button2");
        useButton.setPrefWidth(75);
        Button delButton = new Button("Throw");
        delButton.setId("button2");
        delButton.setPrefWidth(75);
//        buttonPlaceHolder.getChildren().addAll(useButton,delButton);
        itemInfo.getChildren().addAll(image,idLabel,firstLine,secondLine,thirdLine,fourthLine,fifthLine, useButton, delButton);

        if (item instanceof Engimon){
            btnDummy = new Button("Rename");
            btnDummy.setId("button2");
            btnDummy.setPrefWidth(75);
            itemInfo.getChildren().add(btnDummy);
        }
        btnDummy.setOnAction(event -> {
            if (item instanceof Engimon){
                Engimon engimon = (Engimon) item;
                String case1 = InputBox.inputName("Inventory", "Nama Baru Engimon :");
                if (case1.equals("")) return;
                engimon.setName(case1);
                stage.close();
                AlertBox.display("Memakai Skill", "Berhasil Memberi Nama Engimon!");
            }
        });

        useButton.setOnAction(event -> {
            if (item instanceof Skill){
                Engimon toLearn = player.getEngimon();
                if (toLearn != null){
                    if (toLearn.learnSkill((Skill) item)){
                        player.getInventory().removeSkill(id + 1);
                        stage.close();
                        AlertBox.display("Memakai Skill", "Skill Berhasil Dipelajari");
                        prevStage.setScene(createInventoryScene(player, InventoryType.SKILL, prevStage));
                    }else {
                        AlertBox.display("Memakai Skill", "Skill Gagal Digunakan");
                    }
                }else{
                    AlertBox.display("Memakai Skill", "Tidak Ada Engimon Aktif");
                }
            }
            else if (item instanceof Engimon){
                Engimon untukDipakai = player.getInventory().listEngimon().get(id);
                player.setActiveEngimon(untukDipakai);
                stage.close();
                prevStage.close();
            }
        });

        delButton.setOnAction(event -> {
            if (item instanceof Skill){
                Integer input1 = InputBox.inputPrompt("Buang Skill", "Jumlah Skill Untuk Dibuang :");
                int count = player.getInventory().getCountSkill((Skill)item);
                if (input1 == -1) {
                    return;
                }
                if (input1 > count){
                    AlertBox.display("Buang Skill", "Jumlah Skill Untuk Dibuang Melebihi Jumlah yang Dimiliki!");
                    return;
                }
                for (int i = 0; i < input1; i++) {
                    player.getInventory().removeSkill(id + 1);
                }
                stage.close();
                AlertBox.display("Buang Skill", item.getFirstLine() + " Berhasil Dibuang Sebanyak : " + input1);
                prevStage.setScene(createInventoryScene(player, InventoryType.SKILL, prevStage));
            }
            else if (item instanceof Engimon){
                Engimon currEngimon = player.getEngimon();
                Engimon dibuang = player.getInventory().listEngimon().get(id);
                player.getInventory().removeEngimon(id + 1);
                if (currEngimon == dibuang){
                    player.setActiveEngimon(null);
                }
                stage.close();
                AlertBox.display("Buang Engimon", "Engimon Berhasil Dibuang");
                prevStage.setScene(createInventoryScene(player, InventoryType.ENGIMON, prevStage));
            }
        });



        Scene infoScene =new Scene(itemInfo);
        infoScene.getStylesheets().add("assets/styles.css");
        itemInfo.setMinWidth(400);

        stage.getIcons().add(item.getImage());
        stage.setTitle(item.getFirstLine());
        stage.setScene(infoScene);
        stage.setResizable(false);
        stage.showAndWait();
        //-2*(10+75))/2
//        buttonPlaceHolder.setTranslateX(itemInfo.getWidth());

    }
}
