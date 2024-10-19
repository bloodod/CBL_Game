import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class FrontPage implements ActionListener {
    JFrame frame;
    JButton button1;
    JButton button2;
    JLabel title;
    BackgroundPanel backgroundPanel; 

    FrontPage() {
        frame = new JFrame("Chimpanzee Remembers");
        button1 = new TransparentButton("Play", new Color(34, 139, 34));  // Initialize the button
        button2 = new TransparentButton("Tutorial", new Color(34, 139, 34));
        title = new JLabel("Chimpanzee Game");
        backgroundPanel = new BackgroundPanel("resources/banana_background.jpeg");
        backgroundPanel.setLayout(null);

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

        title.setBounds(280,50,400,40);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30)); // Bigger playful font
        title.setForeground(new Color(101, 67, 33)); // Dark brown text


        backgroundPanel.add(button1);
        backgroundPanel.add(button2);
        backgroundPanel.add(title);

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
            frame.dispose();
            GamePage gamePage = new GamePage();
        }

        if(e.getSource() == button2){
            frame.dispose();
            TutorialPage tutorialPage = new TutorialPage();
        }
    }

    

}
