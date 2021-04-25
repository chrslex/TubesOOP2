package oopokemon.misc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.getIcons().add(new Image("assets/oopokemon.png"));
        window.setMinWidth(250);
        window.setMinHeight(100);

        Label label = new Label(message);

        VBox layout = new VBox();
        layout.setPadding(new Insets(10,10,10,10));
        layout.getStylesheets().add("assets/styles.css");
        layout.setAlignment(Pos.CENTER);
        label.setId("label");
        layout.getChildren().add(label);



        Scene scene = new Scene(layout);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }
//    public static void main(String[] args) {
//        display("WARNING!!", "GAGAL");
//    }
}
