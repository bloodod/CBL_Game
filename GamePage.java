import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.io.File;
import java.util.Random;

public class GamePage implements ActionListener {

    /**
     * Initializing the GUI.
     */
    JFrame frame;
    JLabel label;
    JButton revealButton;
    JButton[][] gridButtons;
    JPanel gridPanel;
    BackgroundPanel backgroundPanel;

    static final int SIZE = 5; // Grid size
    int numberAmount = 1; // Amount of numbers on the grid
    int convertSeconds = 1000; // From milliseconds to seconds
    int maxTime = 3 * convertSeconds; // Time you can view the numbers

    int currentNumber = 1; // To check the clicking order
    String buttonNumber;
    int clickedNumber; // To compare with the current number
    int roundCounter = 1; // Number of rounds
    boolean[][] clickedButtons; //Tracking the clicked buttons

    Integer[][] hiddenNumbers; // Stores the numbers hidden from the player
    Timer timer; // Timer to hide the numbers
    Timer delay; // For when the round is over
    ArrayList<ImageIcon> imageList; //Store 25 images
   
    /**
     * Green and red images.
     */
    File greenFile;
    File redFile;
    ImageIcon greenIcon;
    ImageIcon redIcon;

    MusicPlayer musicPlayer;

    Random random = new Random();

    GamePage(MusicPlayer musicPlayer) {

        this.musicPlayer = musicPlayer;

        frame = new JFrame("Game");

        backgroundPanel = new BackgroundPanel("resources/banana_background.jpeg");
        backgroundPanel.setLayout(new BorderLayout());

        revealButton = new TransparentButton("Power-up gamble", new Color(34, 139, 34));
        gridPanel = new JPanel();
        label = new JLabel("Round: " + roundCounter);

        label.setBounds(350, 50, 200, 50);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 25)); // Bigger playful font
        label.setForeground(new Color(101, 67, 33)); // Dark brown text

        revealButton.setPreferredSize(new Dimension(200, 40));
        revealButton.setFocusable(false);
        revealButton.addActionListener(this);
        revealButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        revealButton.setForeground(Color.WHITE);

        gridPanel.setLayout(new GridLayout(SIZE, SIZE, 5, 5));
        gridPanel.setOpaque(false);
        gridButtons = new JButton[SIZE][SIZE];
        hiddenNumbers = new Integer[SIZE][SIZE]; // Initialize the hidden number storage

        imageList = new ArrayList<>();
        loadImages();

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

        musicPlayer.stop();
        musicPlayer.close();
        musicPlayer.setNewTrack("Moonlight_Sonata 3.wav");
        musicPlayer.setVolume(MusicPlayer.getCurrentVolume());
        musicPlayer.play();

        clickedButtons = new boolean[SIZE][SIZE];

        InitializeGrid();

        backgroundPanel.add(label, BorderLayout.NORTH);
        backgroundPanel.add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  // Center the button
        buttonPanel.setOpaque(false);
        buttonPanel.add(revealButton);
    
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(backgroundPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == revealButton) {
            if (e.getSource() == revealButton) {
                int randomNumber = random.nextInt(4) + 1;
                // randomNumber = 1;
                if (randomNumber == 1) {
                    triggerReshow();
                    revealButton.setEnabled(false);
                } else {
                    musicPlayer.stop();
                    musicPlayer.close();
                    frame.dispose(); 
                    EndPage endPage = new EndPage(roundCounter - 1, musicPlayer);
                }
            }

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
                            clickedButtons[i][j] = true; // Mark as clicked

                            // When all the buttons have been clicked in order
                            if (currentNumber > numberAmount) {

                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        delay = new Timer(200, new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                numberAmount++;
                                                roundCounter++;
                                                resetForNextRound();
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
                                    delay = new Timer(1000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //Change to end screen
                                            musicPlayer.stop();
                                            musicPlayer.close();
                                            frame.dispose(); 
                                            EndPage endPage = new EndPage(roundCounter - 1, musicPlayer); 
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
                                delay = new Timer(1000, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                         //Change to end screen
                                        musicPlayer.stop();
                                        musicPlayer.close();
                                        frame.dispose(); 
                                        EndPage endPage = new EndPage(roundCounter - 1, musicPlayer);
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

    /**
     * This function resets the grid.
     * The numbers will go into new random positions
     */
    public void resetForNextRound() {
        currentNumber = 1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                clickedButtons[i][j] = false; // Reset all buttons to "unclicked"
            }
        }

        gridPanel.removeAll();
        InitializeGrid();

        label.setText("Round: " + roundCounter);
        gridPanel.revalidate(); // Layout update
        gridPanel.repaint(); // Visual update
    }

    /**
     * This function creates the grid. 
     * The numbers are put into random places by randomizing the
     * array.
     */
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
                         // Store the number for later comparison
                        hiddenNumbers[i][j] = numbers.get(index);
                        gridButtons[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 24));
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

        // Start the timer to hide the numbers after 3 seconds
        startHideNumbersTimer();

    }

    /**
     * This function starts the timer of 3 seconds.
     * After the 3 seconds the numbers get hidden
     * and the button icons get changed
     */
    private void startHideNumbersTimer() {
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

    /**
     * This function hides the numbers when the 
     * icon gets set.
     */
    private void hideNumbers() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (hiddenNumbers[i][j] != null) {
                    gridButtons[i][j].setText(""); // Clearing the text
                }
            }
        }
    }

    /**
     * This function enables the buttons after 
     * a time delay. This is used after the delay
     * of 3 seconds.
     */
    private void enableButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!clickedButtons[i][j]) {
                    gridButtons[i][j].setEnabled(true);
                }
            }
        }
    }

    /**
     * This function loads the images that are
     * on the buttons.
     */
    private void loadImages() {

        // Topleft image is number 25
        for (int i = SIZE * SIZE; i >= 1; i--) {
            // Image file names like 25.png, 24.png, etc.
            String imageName = i + ".png";

            File imageFile = new File("resources/" + imageName);

            if (!imageFile.exists()) {
                System.out.println("Image not found: " + imageName);
                continue; // Skip if the image is not found
            }

            // Load and resize the image
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            Image image = icon.getImage();
            Image resizedImage = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            imageList.add(resizedIcon);
            System.out.println("Successfully loaded and resized: " + imageName);
        }
    }

    /**
     * This function sets the image of the buttons.
     */
    private void changeToImage() {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!clickedButtons[i][j]) {
                gridButtons[i][j].setIcon(imageList.get(counter));
                }
                counter++;
            }
        }
    }


    /**
     * Showing the numbers again after they've been hidden.
     * This is used when the power-up works.
     */
    public void reshowNumbers() {
        // Loop through the grid buttons
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!clickedButtons[i][j]) {
                    gridButtons[i][j].setIcon(null);
                }

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

    /**
     * This function reshows the numbers first and
     * then changes the buttons back into the images.
     */
    public void triggerReshow() {
        reshowNumbers(); // Call the method to reshow the numbers

        Timer timer = new Timer(maxTime, new ActionListener() {
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
