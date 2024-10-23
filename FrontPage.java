import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.sound.sampled.*;


public class FrontPage implements ActionListener {
    JFrame frame;
    JButton button1;
    JButton button2;
    JButton volumeButton;
    JSlider volumeSlider;
    JLabel title;
    BackgroundPanel backgroundPanel; 
    MusicPlayer musicPlayer;

    FrontPage() {
        frame = new JFrame("Chimpanzee Remembers");
        button1 = new TransparentButton("Play", new Color(34, 139, 34));  // Initialize the button
        button2 = new TransparentButton("Tutorial", new Color(34, 139, 34));
        title = new JLabel("Chimpanzee Game");
        backgroundPanel = new BackgroundPanel("resources/banana_background.jpeg");
        backgroundPanel.setLayout(null);
        musicPlayer = new MusicPlayer("Moonlight_Sonata 1.wav");
        musicPlayer.play();


        button1.setBounds(300,150,200,40);
        button1.setFocusable(false);
        button1.addActionListener(this);
        button1.setFont(new Font("Comic Sans MS", Font.BOLD, 18)); // Playful font
        button1.setForeground(Color.WHITE); // White text


        button2.setBounds(300,300,200,40);
        button2.setFocusable(false);
        button2.addActionListener(this);
        button2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        button2.setForeground(Color.WHITE); // White text       

        title.setBounds(275,50,400,40);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30)); // Bigger playful font
        title.setForeground(new Color(101, 67, 33)); // Dark brown text

        volumeButton = new JButton(new ImageIcon("resources/sound_icon.png"));
        volumeButton.setBounds(700, 500, 50, 50);
        volumeButton.addActionListener(this);

        volumeSlider = new JSlider(0, 100, 100);
        volumeSlider.setBounds(550, 500, 150, 50);
        volumeSlider.addChangeListener(e -> adjustVolume(volumeSlider.getValue()));


        backgroundPanel.add(button1);
        backgroundPanel.add(button2);
        backgroundPanel.add(title);
        backgroundPanel.add(volumeButton);
        backgroundPanel.add(volumeSlider);

        frame.setContentPane(backgroundPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);      //Set window to the middle of screen
        frame.setVisible(true);
    }

    private void adjustVolume(int volumeLevel) {
        float volume = volumeLevel / 100f;
        musicPlayer.setVolume(volume);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            musicPlayer.stop();
            musicPlayer.close();
            frame.dispose();
            GamePage gamePage = new GamePage();
        }

        if(e.getSource() == button2){
            musicPlayer.stop();
            musicPlayer.close();
            frame.dispose();
            TutorialPage tutorialPage = new TutorialPage();
        }
    }

    

}
