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
    JLabel volumeLabel;
    JLabel title;
    BackgroundPanel backgroundPanel; 
    MusicPlayer musicPlayer;
    ImageIcon soundIcon;

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

        try {
            ImageIcon icon = new ImageIcon("resources/sound_icon.png");
            Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale image
            soundIcon = new ImageIcon(img); // Apply scaled image to icon
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        volumeButton = new JButton(soundIcon);
        volumeButton.setBounds(700, 500, 50, 50);
        volumeButton.addActionListener(this);

        int currentVolume = (int) (MusicPlayer.getCurrentVolume() * 100);
        volumeSlider = new JSlider(0, 100, currentVolume);
        volumeSlider.setBounds(550, 500, 150, 50);
        volumeSlider.setVisible(false);
        volumeSlider.addChangeListener(e -> musicPlayer.adjustVolume(volumeSlider.getValue()));

        volumeLabel = new JLabel("Volume: " + volumeSlider.getValue());
        volumeLabel.setBounds(550, 475, 100, 30);
        volumeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        volumeLabel.setVisible(false);
        volumeSlider.addChangeListener(e -> volumeLabel.setText("Volume: " + volumeSlider.getValue()));


        backgroundPanel.add(button1);
        backgroundPanel.add(button2);
        backgroundPanel.add(title);
        backgroundPanel.add(volumeButton);
        backgroundPanel.add(volumeSlider);
        backgroundPanel.add(volumeLabel);

        frame.setContentPane(backgroundPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);      //Set window to the middle of screen
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            // musicPlayer.stop();
            // musicPlayer.close();
            frame.dispose();
            GamePage gamePage = new GamePage(musicPlayer);
        }

        if(e.getSource() == button2){
            musicPlayer.stop();
            // musicPlayer.close();
            frame.dispose();
            TutorialPage tutorialPage = new TutorialPage();
        }

        
        if (e.getSource() == volumeButton) {
            boolean isVisible = volumeSlider.isVisible();
            volumeSlider.setVisible(!isVisible);
            volumeLabel.setVisible(!isVisible);
        }
    }

    

}
