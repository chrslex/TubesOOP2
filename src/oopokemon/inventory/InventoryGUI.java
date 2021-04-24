package oopokemon.inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oopokemon.inventory.*;
import oopokemon.species.Engimon;

import java.util.List;

public class InventoryGUI extends Pane {
    public static void createInventInventory(List<Engimon> engimons){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        HBox inventoryLayout = new HBox();
        inventoryLayout.setAlignment(Pos.CENTER);
        Scene invScene = new Scene(inventoryLayout);
        GridPane inventoryItems = new GridPane();
        ScrollPane invScrollItem = new ScrollPane(inventoryItems);
        invScrollItem.setFitToWidth(true);
        invScrollItem.setMaxHeight(650);



        Pane itemInfo = new Pane();

        inventoryItems.setPadding(new Insets(10,10,10,10));
        inventoryItems.setHgap(10);
        inventoryItems.setVgap(10);
        inventoryItems.setAlignment(Pos.CENTER);


        itemInfo.setPrefSize(300,600);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                inventoryItems.add(new InventroyItem(), i, j);
            }
        }



        inventoryLayout.getChildren().addAll(invScrollItem, itemInfo);
        stage.setScene(invScene);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public static void main(String[] args) {

    }
}
