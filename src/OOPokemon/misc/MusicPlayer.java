package OOPokemon.misc;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicPlayer extends Thread {
    private MediaPlayer mediaPlayer;

    public MusicPlayer(String namaFile){
        mediaPlayer = new MediaPlayer(new Media(Paths.get(namaFile).toUri().toString()));
    }

    @Override
    public void run() {
        mediaPlayer.play();
    }
}
