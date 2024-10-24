import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    /**
     * Creating variables for music and volume.
     */
    private Clip clip;
    FloatControl volumeControl;
    private static float currentVolume = 1.0f;

    /*
     * This function inputs the name of the song to load.
     */
    public MusicPlayer(String fileName) {
        loadClip(fileName);
    }
    
    /**
     * This function loads the song and gets the volume from the user.
     * @param fileName
     */
    public void loadClip(String fileName) {
        try {
            File musicPath = new File("resources/" + fileName);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(currentVolume);
            } else {
                System.out.println("Error: File not found - " + fileName);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function plays the song continuously.
     */
    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * This function stops the song.
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * This function closes the song.
     */
    public void close() {
        if (clip != null) {
            clip.close();
            clip = null;
        }
    }

    /**
     * This function sets the volume
     * using a log scale for smoother volume changes.
     * @param volume
     */
    public void setVolume(float volume) {
        currentVolume = volume;
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float dbVolume = min + (max - min) * (float) (Math.log10(volume * 9 + 1) / Math.log10(10));
            volumeControl.setValue(dbVolume);
        }
    }

    /**
     * This function adjusts the volume to what the user wants.
     * @param volumeLevel
     */
    public void adjustVolume(int volumeLevel) {
        float volume = volumeLevel / 100f;
        setVolume(volume);
    }

    /**
     * This function returns the volume so it can be
     * used as a variable in other classes.
     * @return
     */
    public static float getCurrentVolume() {
        return currentVolume;
    }

    /**
     * This function changes the song to a different one.
     * @param fileName
     */
    public void setNewTrack(String fileName) {
        stop();
        close();
        loadClip(fileName);
    }
}
