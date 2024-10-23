import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private Clip clip;
    FloatControl volumeControl;

    public MusicPlayer(String fileName) {
        try {
            File musicPath = new File("resources/" + fileName);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                System.out.println("Error: File not found - " + fileName);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
    
    public void setVolume(float level) {
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        float volume = min + (max - min) * level;
        volumeControl.setValue(volume); // Set volume
    }
    
}
