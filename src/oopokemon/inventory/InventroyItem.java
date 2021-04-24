package oopokemon.inventory;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InventroyItem extends Pane {
    private final ImageView center;
    private final Button button;
    public InventroyItem(){
        super();
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
    }

    public void setImage(Image image){
        center.setImage(image);
    }
}
