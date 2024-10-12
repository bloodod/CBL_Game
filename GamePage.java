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
    int convertSeconds = 1000; //from milliseconds to seconds
    int maxTime = 3 * convertSeconds;

    int currentNumber = 1;
    String buttonNumber;
    int buttonNumberStringToInt;
    

    GamePage() {

        frame = new JFrame("Game");
        label = new JLabel("Game Page");
        backButton = new JButton("Exit this Game");
        gridPanel = new JPanel();

        label.setBounds(350,50,200,50);
        label.setFont(new Font(null,Font.PLAIN,25));

        backButton.setBounds(300,500,200,40);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        gridPanel.setLayout(new GridLayout(SIZE, SIZE, 5, 5));
        gridButtons = new JButton[SIZE][SIZE];

        InitializeGrid();

        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);

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

        for (int i = 0; i < SIZE; i++ ) {
            for (int j = 0; j < SIZE; j++) {
                buttonNumber = gridButtons[i][j].getText();
                buttonNumberStringToInt = Integer.parseInt(buttonNumber);
                if (buttonNumberStringToInt == currentNumber) {
                    currentNumber++;
                } else {
                    JOptionPane.showMessageDialog(frame,"You lost!");
                }
            }
        }

    }

    public void InitializeGrid() {
        ArrayList<Integer> numbers = new ArrayList<>();

        //Creating numbers and blanks for on the grid
        for (int i = 1; i < numberAmount; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < SIZE * SIZE - numberAmount + 1; i++) {
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
