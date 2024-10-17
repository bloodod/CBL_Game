import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EndPage implements ActionListener {
    JFrame frame;
    JButton buttonPlayAgain;
    JButton buttonExit;
    JLabel highscoreLabel;
    JLabel scoreLabel;


    EndPage() {
        frame = new JFrame("End screen");
        buttonPlayAgain = new JButton("Play again");
        buttonExit = new JButton("Exit");
        highscoreLabel = new JLabel("Highscore: ");
        scoreLabel = new JLabel("Your score: ");

        buttonPlayAgain.setBounds(300,150,200,40);
        buttonPlayAgain.setFocusable(false);
        buttonPlayAgain.addActionListener(this);

        buttonExit.setBounds(300,300,200,40);
        buttonExit.setFocusable(false);
        buttonExit.addActionListener(this);

        highscoreLabel.setBounds(300,50,400,40);
        highscoreLabel.setFont(new Font(null,Font.PLAIN,25));

        frame.add(buttonPlayAgain);
        frame.add(buttonExit);
        frame.add(highscoreLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);      //Set window to the middle of screen
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPlayAgain) {
            frame.dispose();
            GamePage gamePage = new GamePage();
        }

        if (e.getSource() == buttonExit) {
            frame.dispose();
        }
    }
}
