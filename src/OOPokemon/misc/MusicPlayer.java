package OOPokemon.misc;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class MusicPlayer extends Thread {

    private MediaPlayer mediaPlayer;

    private AtomicBoolean running = new AtomicBoolean(false);


    /**
     * @param namaFile is the location where the music file is located relative to the project folder
     * @param loop true for loop, false for play only once
     */
    public MusicPlayer(String namaFile, boolean loop){
        mediaPlayer = new MediaPlayer(new Media(Paths.get(namaFile).toUri().toString()));
        if (loop) {
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        }
        mediaPlayer.setVolume(0.7);
    }

    @Override
    public void run() {
        running.set(true);
        mediaPlayer.play();
    }

    @Override
    public void interrupt() {
        super.interrupt();
        mediaPlayer.stop();
    }

    public void playpause() {
        if (running.get()) {
            running.set(false);
            mediaPlayer.pause();
            return;
        }
        running.set(true);
        mediaPlayer.play();

    }
}
