import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class FrontPage implements ActionListener {
    JFrame frame;
    JButton button1;
    JButton button2;
    JLabel title;

    FrontPage() {
        frame = new JFrame("Chimpanzee Remembers");
        button1 = new JButton("Play");  // Initialize the button
        button2 = new JButton("Tutorial");
        title = new JLabel("Chimpanzee Game");

        button1.setBounds(300,150,200,40);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2.setBounds(300,300,200,40);
        button2.setFocusable(false);
        button2.addActionListener(this);

        title.setBounds(300,50,400,40);
        title.setFont(new Font(null,Font.PLAIN,25));

        frame.add(button1);
        frame.add(button2);
        frame.add(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
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
