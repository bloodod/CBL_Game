import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;

public class GamePage implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton backButton;
    JButton[][] gridButtons;
    JPanel gridPanel;

    static final int SIZE = 5;
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
        ArrayList<Integer> numbers = new ArrayList<>();

        //Creating numbers and blanks for on the grid
        for (int i = 0; i < numberAmount; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < SIZE * SIZE - numberAmount; i++) {
            numbers.add(null);
        }

        //List gets shuffled for random positions on the grid
        Collections.shuffle(numbers);

        int index = 0;

        //Going through the whole grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (index < numbers.size()) {
                    //Putting the number on the button
                    if (numbers.get(index) != null) {
                        gridButtons[i][j] = new JButton(String.valueOf(numbers.get(index)));
                    } else {        //Empty if not a number
                        gridButtons [i][j] = new JButton("");
                    }
                    gridButtons[i][j].setFocusable(false);
                    gridButtons[i][j].addActionListener(this);
                    gridPanel.add(gridButtons[i][j]);
                    index++;
                }
            }
        }

        gridPanel.setBounds(100, 150, 600, 300);


    }
}
