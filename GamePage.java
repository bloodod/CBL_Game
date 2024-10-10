import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;

public class GamePage implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton backButton;
    JButton[][] gridButtons;

    static final int SIZE = 10;
    int numberAmount = 10;
    

    GamePage() {

        frame = new JFrame("Game");
        label = new JLabel("Game Page");
        backButton = new JButton("Exit this Game");

        label.setBounds(350,50,200,50);
        label.setFont(new Font(null,Font.PLAIN,25));

        backButton.setBounds(300,500,200,40);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        frame.add(label);
        frame.add(backButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            frame.dispose();
            FrontPage frontPage = new FrontPage();
        }

    }

    public void InitializeGrid() {


    }
}
