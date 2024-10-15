import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
//test

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
    int clickedNumber;
    

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

                //Check if the button is numbered
                if (e.getSource() == gridButtons[i][j]) {
                    buttonNumber = gridButtons[i][j].getText();

                    //Get the number when it has one
                    if (!buttonNumber.isEmpty()) {
                        clickedNumber = Integer.parseInt(buttonNumber);

                        // Check if the numbers are clicked in order
                        if (clickedNumber == currentNumber) {
                            currentNumber++;
                            gridButtons[i][j].setEnabled(false);
                            
                            //When all the buttons have been clicked in order
                            if (currentNumber > numberAmount - 1) {
                                JOptionPane.showMessageDialog(frame, "Round complete!");
                                ResetForNextRound();
                            } 
                        } else {
                            JOptionPane.showMessageDialog(frame, "You lost!");
                            ResetForNextRound();
                        }
                    }
                }               
            }
        }

    }

    public void ResetForNextRound() {
        currentNumber = 1;
        gridPanel.removeAll();
        InitializeGrid();
        gridPanel.revalidate();
        gridPanel.repaint();
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
