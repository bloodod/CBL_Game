import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private Clip clip;
    FloatControl volumeControl;
    private static float currentVolume = 1.0f;

    public MusicPlayer(String fileName) {
        loadClip(fileName);
    }
    
    public void loadClip(String fileName) {
        try {
            File musicPath = new File("resources/" + fileName);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(currentVolume);
                System.out.println("MusicPlayer initialized: " + fileName);
            } else {
                System.out.println("Error: File not found - " + fileName);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Error initializing MusicPlayer: " + e.getMessage());
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Music started: " + clip.isRunning()); // Debugging
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            System.out.println("Music stopped: " + !clip.isRunning()); // Debugging
        } else {
            System.out.println("Music was already stopped or clip is null."); // Debugging
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
            clip = null;
            System.out.println("Music closed."); // Debugging
        } else {
            System.out.println("Clip was already null or not initialized."); // Debugging
        }
    }

    public void setVolume(float volume) {
        currentVolume = volume;
        if(volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float dbVolume = min + (max - min) * (float) (Math.log10(volume * 9 + 1) / Math.log10(10));
            volumeControl.setValue(dbVolume);
        }
    }

    public void adjustVolume(int volumeLevel) {
        float volume = volumeLevel / 100f;
        setVolume(volume);
    }

    public static float getCurrentVolume() {
        return currentVolume;
    }
    
    public void setNewTrack(String fileName) {
        stop();
        close();
        loadClip(fileName);
    }
}
