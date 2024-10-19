import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.io.File;

public class GamePage implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton backButton;
    JButton[][] gridButtons;
    JPanel gridPanel;

    static final int SIZE = 5; // Grid size
    int numberAmount = 1; // Amount of numbers on the grid
    int convertSeconds = 1000; // From milliseconds to seconds
    int maxTime = 3 * convertSeconds; // Time you can view the numbers

    int currentNumber = 1; // To check the clicking order
    String buttonNumber;
    int clickedNumber;
    int roundCounter = 1; // Number of rounds

    Integer[][] hiddenNumbers; // Stores the numbers hidden from the player
    Timer timer; // Timer to hide the numbers
    Timer delay; // For when the round is over
    ArrayList<ImageIcon> imageList; //Store 25 images
    File greenFile;
    File redFile;
    ImageIcon greenIcon;
    ImageIcon redIcon;

    MusicPlayer musicPlayer;

    GamePage() {

        frame = new JFrame("Game");
        backButton = new JButton("Exit this Game");
        gridPanel = new JPanel();
        label = new JLabel("Round: " + roundCounter);

        label.setBounds(350, 50, 200, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        backButton.setBounds(300, 500, 200, 40);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        gridPanel.setLayout(new GridLayout(SIZE, SIZE, 5, 5));
        gridButtons = new JButton[SIZE][SIZE];
        hiddenNumbers = new Integer[SIZE][SIZE]; // Initialize the hidden number storage

        imageList = new ArrayList<>();
        LoadImages();

        greenFile = new File("resources/green.png");
        redFile = new File("resources/red.png");
        
        if (greenFile.exists()) {
            greenIcon = new ImageIcon(greenFile.getAbsolutePath());
            System.out.println("Successfully loaded green.png");
        } else {
            System.out.println("green.png not found!");
        }
        
        if (redFile.exists()) {
            redIcon = new ImageIcon(redFile.getAbsolutePath());
            System.out.println("Successfully loaded red.png");
        } else {
            System.out.println("red.png not found!");
        }

        musicPlayer = new MusicPlayer("Moonlight_Sonata 3.wav");
        musicPlayer.play();

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
            // musicPlayer.stop();
            // musicPlayer.close();
            // frame.dispose();
            // FrontPage frontPage = new FrontPage();
            triggerReshow();;
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
                            gridButtons[i][j].setDisabledIcon(greenIcon);
                            gridButtons[i][j].setEnabled(false); // Disable the button once clicked

                            // When all the buttons have been clicked in order
                            if (currentNumber > numberAmount) {

                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Create a Timer to introduce a delay before moving to the end page
                                        delay = new Timer(200, new ActionListener() { // 1000 ms = 1 second delay
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                numberAmount++;
                                                roundCounter++;
                                                ResetForNextRound();
                                            }
                                        });
                                        delay.setRepeats(false); // Only run once after the delay
                                        delay.start(); // Start the timer
                                    }
                                });
                            }
                        } else {
                            // When the wrong order is clicked
                            gridButtons[i][j].setDisabledIcon(redIcon);
                            gridButtons[i][j].setEnabled(false);
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    // Create a Timer to introduce a delay before moving to the end page
                                    delay = new Timer(1000, new ActionListener() { // 1000 ms = 1 second delay
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            musicPlayer.stop();
                                            musicPlayer.close();
                                            frame.dispose(); 
                                            EndPage endPage = new EndPage(roundCounter - 1); //Change to end screen
                                        }
                                    });
                                    delay.setRepeats(false); // Only run once after the delay
                                    delay.start(); // Start the timer
                                }
                            });

                        }
                    } else {
                        // When a button is clicked that had no number
                        gridButtons[i][j].setDisabledIcon(redIcon);
                        gridButtons[i][j].setEnabled(false);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                // Create a Timer to introduce a delay before moving to the end page
                                delay = new Timer(1000, new ActionListener() { // 1000 ms = 1 second delay
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        musicPlayer.stop();
                                        musicPlayer.close();
                                        frame.dispose(); 
                                        EndPage endPage = new EndPage(roundCounter - 1); //Change to end screen
                                    }
                                });
                                delay.setRepeats(false); // Only run once after the delay
                                delay.start(); // Start the timer
                            }
                        });
                    }
                }
            }
        }
    }

    public void ResetForNextRound() {
        currentNumber = 1;
        gridPanel.removeAll();
        InitializeGrid();

        label.setText("Round: " + roundCounter);
        gridPanel.revalidate(); // Layout update
        gridPanel.repaint(); // Visual update
    }

    public void InitializeGrid() {
        ArrayList<Integer> numbers = new ArrayList<>();

        // Creating numbers for the grid
        for (int i = 1; i <= numberAmount; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < SIZE * SIZE - numberAmount; i++) {
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
                changeToImage();
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

    private void changeToImage() {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridButtons[i][j].setIcon(imageList.get(counter));
                counter++;
            }
        }
    }

    private void LoadImages() {

        // Topleft image is number 25
        for (int i = SIZE * SIZE; i >= 1; i--) {
            // Image file names like 25.png, 24.png, etc.
            String imageName = i + ".png";
            
            // Use relative file path for testing
            File imageFile = new File("resources/" + imageName);
            
            if (!imageFile.exists()) {
                System.out.println("Image not found: " + imageName);
                continue; // Skip if the image is not found
            }
            
            // Load and resize the image
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath()); // Load using absolute file path
            Image image = icon.getImage();
            Image resizedImage = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH); // Resize
            ImageIcon resizedIcon = new ImageIcon(resizedImage); // Convert back to ImageIcon
            
            imageList.add(resizedIcon);
            System.out.println("Successfully loaded and resized: " + imageName);
        }
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void reshowNumbers() {
        // Loop through the grid buttons
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridButtons[i][j].setIcon(null);

                if (hiddenNumbers[i][j] != null) {

                    gridButtons[i][j].setText(String.valueOf(hiddenNumbers[i][j]));
                } else {
 
                    gridButtons[i][j].setText("");
                }
                // Disable the button to ensure players can't click during the reveal
                gridButtons[i][j].setEnabled(false);
            }
        }
    }


    public void triggerReshow() {
        reshowNumbers(); // Call the method to reshow the numbers

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideNumbers();
                enableButtons();
                changeToImage();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
