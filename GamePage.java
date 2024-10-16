import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class GamePage implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton backButton;
    JButton[][] gridButtons;
    JPanel gridPanel;

    static final int SIZE = 5;
    int numberAmount = 10;
    int convertSeconds = 1000; // from milliseconds to seconds
    int maxTime = 5 * convertSeconds;

    int currentNumber = 1;
    String buttonNumber;
    int clickedNumber;

    Integer[][] hiddenNumbers; // Stores the numbers hidden from the player
    Timer timer; // Timer to hide the numbers

    GamePage() {

        frame = new JFrame("Game");
        label = new JLabel("Game Page");
        backButton = new JButton("Exit this Game");
        gridPanel = new JPanel();

        label.setBounds(350, 50, 200, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        backButton.setBounds(300, 500, 200, 40);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        gridPanel.setLayout(new GridLayout(SIZE, SIZE, 5, 5));
        gridButtons = new JButton[SIZE][SIZE];
        hiddenNumbers = new Integer[SIZE][SIZE]; // Initialize the hidden number storage

        InitializeGrid();

        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            FrontPage frontPage = new FrontPage();
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                // Check if the player clicked a button
                if (e.getSource() == gridButtons[i][j]) {

                    // Get the hidden number stored for this button
                    if (hiddenNumbers[i][j] != null) {
                        clickedNumber = hiddenNumbers[i][j];

                        // Check if the player clicked the correct number
                        if (clickedNumber == currentNumber) {
                            currentNumber++;
                            gridButtons[i][j].setEnabled(false); // Disable the button once clicked

                            // When all the buttons have been clicked in order
                            if (currentNumber > numberAmount - 1) {
                                JOptionPane.showMessageDialog(frame, "Round complete!");
                                ResetForNextRound();
                            }
                        } else {
                            //When the wrong order is clicked
                            JOptionPane.showMessageDialog(frame, "You lost!");
                            ResetForNextRound();
                        }
                    } else {
                        //When a button is clicked that had no number
                        JOptionPane.showMessageDialog(frame, "You lost!");
                        ResetForNextRound();
                    }
                }
            }
        }
    }

    public void ResetForNextRound() {
        currentNumber = 1;
        gridPanel.removeAll();
        InitializeGrid();
        gridPanel.revalidate(); //layout update
        gridPanel.repaint(); // visual update
    }

    public void InitializeGrid() {
        ArrayList<Integer> numbers = new ArrayList<>();

        // Creating numbers for the grid
        for (int i = 1; i < numberAmount; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < SIZE * SIZE - numberAmount + 1; i++) {
            numbers.add(null); // Add empty spaces where there are no numbers
        }

        // Shuffle the numbers for random positions on the grid
        Collections.shuffle(numbers);

        int index = 0;

        // Populate the grid with numbers or blanks
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (index < numbers.size()) {
                    // Set button text and store the number in hiddenNumbers array
                    if (numbers.get(index) != null) {
                        gridButtons[i][j] = new JButton(String.valueOf(numbers.get(index)));
                        hiddenNumbers[i][j] = numbers.get(index); // Store the number for later comparison
                    } else {
                        gridButtons[i][j] = new JButton("");
                        hiddenNumbers[i][j] = null; // No number, so null
                    }
                    gridButtons[i][j].setFocusable(false);
                    gridButtons[i][j].addActionListener(this);
                    gridButtons[i][j].setEnabled(false);
                    gridPanel.add(gridButtons[i][j]);
                    index++;
                }
            }
        }

        gridPanel.setBounds(100, 150, 600, 300);

        // Start the timer to hide the numbers after 5 seconds
        startHideNumbersTimer();
    }

    private void startHideNumbersTimer() {
        // Initialize the timer to hide numbers after 5 seconds
        timer = new Timer(maxTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideNumbers();
                enableButtons();
            }
        });
        timer.setRepeats(false); // Only run once
        timer.start();
    }

    private void hideNumbers() {
        // Hide all numbers by setting text to empty for each button
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (hiddenNumbers[i][j] != null) {
                    gridButtons[i][j].setText(""); // Clear the text, but number is stored in hiddenNumbers
                }
            }
        }
    }

    private void enableButtons() {
        // Enable all buttons after 3 seconds
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridButtons[i][j].setEnabled(true); // Enable the button
            }
        }
    }
}
