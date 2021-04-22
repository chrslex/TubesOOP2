package oopokemon.misc;

import oopokemon.map.Cell;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;



public class Sprite extends Pane {
    private final ImageView center;
    private final ImageView topLeft;
    private final ImageView topRight;
    private final ImageView bottomLeft;
    private final ImageView bottomRight;

    public Sprite() {
        super();
        float width = Cell.cellWidth;
        float height = Cell.cellHeight;
        setMaxSize(width, height);
        
        center = new ImageView();
        center.setFitHeight(width);
        center.setFitWidth(height);
        center.setPreserveRatio(true);
        center.setX(0);
        center.setY(0);


        topLeft = new ImageView();
        topLeft.setFitHeight(height / 3);
        topLeft.setFitWidth(width / 3);
        center.setPreserveRatio(true);
        topLeft.setX(0);
        topLeft.setY(0);


        topRight = new ImageView();
        topRight.setFitHeight(height / 3);
        topRight.setFitWidth(width / 3);
        topRight.setPreserveRatio(true);
        topRight.setX(width * 2/3);
        topRight.setY(0);


        bottomLeft = new ImageView();
        bottomLeft.setFitHeight(height / 3);
        bottomLeft.setFitWidth(width / 3);
        bottomLeft.setPreserveRatio(true);
        bottomLeft.setX(0);
        bottomLeft.setY(height * 2/3);


        bottomRight = new ImageView();
        bottomRight.setFitHeight(height / 2);
        bottomRight.setFitWidth(width / 2);
        bottomRight.setPreserveRatio(true);
        bottomRight.setX(width * 1/2);
        bottomRight.setY(height * 1/2);


        this.getChildren().addAll(center,topLeft, topRight, bottomLeft, bottomRight);
    }

    public void setLowerSize(boolean status){
        float width = Cell.cellWidth;
        float height = Cell.cellHeight;
        if (status){
            center.setFitWidth(0.7 * width);
            center.setFitHeight(0.7 * height);
            center.setX(0.15 * width);
            center.setY(0.15 * height);
            return;
        }
        center.setFitHeight(width);
        center.setFitWidth(height);
        center.setX(0);
        center.setY(0);
    }

    public void setCenterImage(Image image) {
        center.setImage(image);
    }

    public void setTopLefImage(Image image) {
        topLeft.setImage(image);
    }

    public void setTopRightImage(Image image) {
        topRight.setImage(image);
    }

    public void setBottomLeftImage(Image image) {
        bottomLeft.setImage(image);
    }

    public void setBottomRightImage(Image image) {
        bottomRight.setImage(image);
    }
}
