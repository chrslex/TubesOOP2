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
                AlertBox.display("Input Error", "Input Salah");
                return null;
            }
        }
        AlertBox.display("Input Error", "Tidak Memasukkan Input");
        return -1;
    }

    public static String inputName(String title, String message){
        TextInputDialog dialog = new TextInputDialog();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/oopokemon.png"));
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.setContentText("Input :");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        AlertBox.display("Input Error", "Tidak Memasukkan Input");
        return "";
    }
}
