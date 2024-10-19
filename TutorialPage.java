import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TutorialPage implements ActionListener{

    JFrame frame;
    JLabel title;
    JButton backButton;
    BackgroundPanel backgroundPanel; 
    JLabel gifLabel;


    TutorialPage() {

        frame = new JFrame("Tutorial");
        title = new JLabel("How to play");
        backButton = new TransparentButton("Back", new Color(34, 139, 34));

        title.setBounds(350,50,200,50);
        title.setFont(new Font(null,Font.PLAIN,25));

        backButton.setBounds(300,500,200,40);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        ImageIcon monkeyGif = new ImageIcon("resources/MonkeyGIF.gif");
        gifLabel = new JLabel(monkeyGif);
        gifLabel.setBounds(300, 150, 200, 200); 

        backgroundPanel = new BackgroundPanel("resources/banana_background.jpeg");
        backgroundPanel.setLayout(null);
        backgroundPanel.add(backButton);
        backgroundPanel.add(gifLabel);

        frame.add(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setContentPane(backgroundPanel);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            frame.dispose();
            FrontPage frontPage = new FrontPage();
        }

    }
}
