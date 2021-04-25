package oopokemon.inventory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oopokemon.occupier.Player;

public class InventroyItem extends Pane {
    private final ImageView center;
    public final Button button;
    public final int id;
    private final Item item = null;
    private final Player player;
    public InventroyItem(int id, Item item, Player player, Stage prevStage){
        super();
        this.id = id;
        this.player = player;
        setMaxSize(150, 150);
        center = new ImageView();
        button = new Button();
        center.setX(0);
        center.setY(0);
        center.setFitWidth(150);
        center.setFitHeight(150);
        button.setPrefSize(150, 150);
        button.setTranslateX(0);
        button.setTranslateY(0);
        button.setId("inventoryitem");
        getChildren().addAll(center, button);
        setImage(item.getImage());
        button.setOnAction(event -> {
            InventoryGUI.createInfo(item, id, player, prevStage);
            System.out.println(id);
        });
    }

    public void setImage(Image image){
        center.setImage(image);
    }

}
