import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TutorialPage implements ActionListener{

    /**
     * Initializing the GUI.
     */
    JFrame frame;
    JLabel title;
    JButton backButton;
    BackgroundPanel backgroundPanel; 
    JLabel gifLabel;
    JLabel tutorialText;


    TutorialPage() {

        frame = new JFrame("Tutorial");
        title = new JLabel("Tutorial");
        backButton = new TransparentButton("Back", new Color(34, 139, 34));
        tutorialText = new JLabel("<html><body style='text-align:center;'>" +
                "Play like this chimpanzee. You have 3 seconds to see and remember the given numbers.<br>" +
                "Now the sequence of numbers are hidden and you have to click the numbers in order.<br>" +
                "After every round, a new number will be added to the sequence." +
                "</body></html>");

        title.setBounds(300,20,200,50);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 40));

        backButton.setBounds(300,500,200,40);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        ImageIcon monkeyGif = new ImageIcon("resources/MonkeyGIF.gif");
        gifLabel = new JLabel(monkeyGif);
        gifLabel.setBounds(300, 100, 200, 200); 

        tutorialText.setBounds(100, 300, 600, 200);
        tutorialText.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        tutorialText.setForeground(Color.BLACK);

        backgroundPanel = new BackgroundPanel("resources/banana_background.jpeg");
        backgroundPanel.setLayout(null);
        backgroundPanel.add(backButton);
        backgroundPanel.add(gifLabel);
        backgroundPanel.add(tutorialText);
        backgroundPanel.add(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setContentPane(backgroundPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            FrontPage frontPage = new FrontPage();
        }

    }
}
