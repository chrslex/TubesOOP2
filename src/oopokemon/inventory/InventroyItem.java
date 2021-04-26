package oopokemon.inventory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oopokemon.occupier.Player;
import oopokemon.skill.Skill;

public class InventroyItem extends Pane {
    private final ImageView center;
    private final ImageView topRight;
    public final Button button;
    public final int id;

    public InventroyItem(int id, Item item, Player player, Stage prevStage){
        super();
        this.id = id;
        setMaxSize(150, 150);
        center = new ImageView();
        button = new Button();
        topRight = new ImageView();
        topRight.setFitHeight(150 / 3);
        topRight.setFitWidth(150 / 3);
        topRight.setPreserveRatio(true);
        topRight.setX(150 * 2/3);
        topRight.setY(0);
        center.setX(0);
        center.setY(0);
        center.setFitWidth(150);
        center.setFitHeight(150);
        button.setPrefSize(150, 150);
        button.setTranslateX(0);
        button.setTranslateY(0);
        button.setId("inventoryitem");
        getChildren().addAll(center, button, topRight);
        setImage(item.getImage());
        if (item instanceof Skill){
            setTopRight(new Image("assets/" + ((Skill) item).getMasteryLevel() + ".png"));
        }
        button.setOnAction(event -> {
            InventoryGUI.createInfo(item, id, player, prevStage);
            System.out.println(id);
        });
    }

    public InventroyItem(int width, int length){
        super();
        id = 0;
        setMaxSize(width,length);
        center = new ImageView();
        button = new Button();
        topRight = new ImageView();
        center.setX(0);
        center.setY(0);
        center.setFitWidth(width);
        center.setFitHeight(length);

        button.setPrefSize(width, length);
        button.setTranslateX(0);
        button.setTranslateY(0);
        button.setId("pauseItem");
        getChildren().addAll(center, button, topRight);
        button.setId("inventoryitem");
        setImage(new Image("assets/pause.png"));

    }

    public void setTopRight(Image image){
        topRight.setImage(image);
    }

    public void setImage(Image image){
        center.setImage(image);
    }

    public Button getButton(){
        return button;
    }
}
