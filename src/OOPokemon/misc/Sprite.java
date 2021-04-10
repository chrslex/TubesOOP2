package OOPokemon.misc;

import OOPokemon.Map.Cell;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;



public class Sprite extends Pane {
    private final ImageView center;
    private final ImageView topLeft;
    private final ImageView bottomRight;

    public Sprite() {
        super();
        float width = Cell.cellWidth;
        float height = Cell.cellHeight;
        setMaxSize(width, height);
        
        center = new ImageView();
        center.setFitHeight(width);
        center.setFitWidth(height);
        center.setX(0);
        center.setY(0);


        topLeft = new ImageView();
        topLeft.setFitHeight(height / 3);
        topLeft.setFitWidth(width / 3);
        topLeft.setX(0);
        topLeft.setY(0);


        bottomRight = new ImageView();
        bottomRight.setFitHeight(height / 3);
        bottomRight.setFitWidth(width / 3);
        bottomRight.setX(width * 2/3);
        bottomRight.setY(height * 2/3);


        this.getChildren().addAll(center,topLeft,bottomRight);
    }



    public void setCenterImage(Image image) {
        center.setImage(image);
    }

    public void setTopLefImage(Image image) {
        topLeft.setImage(image);
    }

    public void setBottomRightImage(Image image) {
        bottomRight.setImage(image);
    }
}
