import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class EndPage implements ActionListener {

    /**
     * Initializing the GUI.
     */
    JFrame frame;
    JButton buttonPlayAgain;
    JButton buttonExit;
    JButton buttonMainMenu;
    JLabel highscoreLabel;
    JLabel scoreLabel;
    GamePage gamePage;
    MusicPlayer musicPlayer;
    BackgroundPanel backgroundPanel;

    /**
     * Variables for the highscore
     */
    static int highscore = 0;
    static final String HIGHSCORE_FILE = "highscore.txt";

    EndPage(int score, MusicPlayer musicPlayer) {

        this.musicPlayer = musicPlayer;

        loadHighScore();

        if (score > highscore) {
            highscore = score;
            saveHighScore();
        }

        frame = new JFrame("End screen");
        buttonPlayAgain = new TransparentButton("Play again", new Color(34, 139, 34));
        buttonExit = new TransparentButton("Exit", new Color(34, 139, 34));
        buttonMainMenu = new TransparentButton("Main Menu", new Color(34, 139, 34));

        highscoreLabel = new JLabel("Highscore:  " + highscore);
        scoreLabel = new JLabel("Your score: " + (score) );
        backgroundPanel = new BackgroundPanel("resources/banana_background.jpeg");
        backgroundPanel.setLayout(null);

        musicPlayer.stop();
        musicPlayer.close();
        musicPlayer.setNewTrack("Moonlight_Sonata 2.wav");
        musicPlayer.setVolume(MusicPlayer.getCurrentVolume());
        musicPlayer.play();


        buttonPlayAgain.setBounds(300,200,200,40);
        buttonPlayAgain.setFocusable(false);
        buttonPlayAgain.addActionListener(this);
        buttonPlayAgain.setForeground(Color.WHITE);

        buttonMainMenu.setBounds(300,300,200,40);
        buttonMainMenu.setFocusable(false);
        buttonMainMenu.addActionListener(this);
        buttonMainMenu.setForeground(Color.WHITE);

        buttonExit.setBounds(300,400,200,40);
        buttonExit.setFocusable(false);
        buttonExit.addActionListener(this);
        buttonExit.setForeground(Color.WHITE);

        highscoreLabel.setBounds(300,100,400,40);
        highscoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        highscoreLabel.setForeground(new Color(101, 67, 33));

        scoreLabel.setBounds(300, 50, 400, 40);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        scoreLabel.setForeground(new Color(101, 67, 33));

        backgroundPanel.add(buttonPlayAgain);
        backgroundPanel.add(buttonExit);
        backgroundPanel.add(highscoreLabel);
        backgroundPanel.add(scoreLabel);
        backgroundPanel.add(buttonMainMenu);

        frame.setContentPane(backgroundPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // Set window to the middle of screen
        frame.setVisible(true);
    }

    // Load the high score from a file
    private void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                highscore = Integer.parseInt(line);
            }
        } catch (IOException e) {
            // File does not exist yet or an error occurred
            System.out.println("No highscore file found");
        }
    }

    /**
     *  This function saves the high score to a file.
     */
    private void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            writer.write(String.valueOf(highscore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPlayAgain) {
            musicPlayer.stop();
            musicPlayer.close();
            frame.dispose();
            GamePage gamePage = new GamePage(musicPlayer);
        }

        if (e.getSource() == buttonExit) {
            musicPlayer.stop();
            musicPlayer.close();
            frame.dispose();
        }

        if (e.getSource() == buttonMainMenu) {
            musicPlayer.stop();
            musicPlayer.close();
            frame.dispose();
            FrontPage frontPage = new FrontPage();
        }


    }


}
