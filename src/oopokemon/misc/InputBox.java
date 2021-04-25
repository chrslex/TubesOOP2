package oopokemon.misc;

import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class InputBox {
    public static Integer inputPrompt(String title, String message){
        TextInputDialog dialog = new TextInputDialog();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/oopokemon.png"));
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.setContentText("Input :");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                String inputText = result.get();
                return Integer.valueOf(inputText);
            }catch (NumberFormatException e){
                AlertBox.display("input error", "Input salah");
                return null;
            }
        }
        AlertBox.display("input error", "tidak memasukkan input");
        return -1;
    }
}
