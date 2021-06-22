package com.brickbreaker.start;

import javax.swing.*;
import java.awt.*;

public class StartScreenPanel extends JPanel {
    private JLabel gameInstructionsLabel;
    private JTextArea instructionsTextArea;
    private JLabel selectLevelLabel;

    private JToggleButton easyToggleButton;
    private JToggleButton mediumToggleButton;
    private JToggleButton hardToggleButton;
    private final ButtonGroup gameModeButtonGroup = new ButtonGroup();

    private JButton startButton;

    public StartScreenPanel () {
        initializeUI();
    }

    // Initialize the UI
    private void initializeUI() {
        // setting up the panel background
        setBackground(new Color(154, 205, 50));
        setPreferredSize(new Dimension(800, 1000));
        setLayout(null); // no layout assigned, it puts the panel at specified x, y coordinates

        // instructions label
        gameInstructionsLabel = new JLabel("GAME INSTRUCTIONS:");
        gameInstructionsLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        gameInstructionsLabel.setBounds(282, 36, 309, 42);
        add(gameInstructionsLabel);

        // instructions list
        instructionsTextArea = new JTextArea();
        instructionsTextArea.setText(
                "Read the instructions\n\n" +
                        "Select the difficulty level, there are 3 levels: easy, medium, hard\n" +
                        "In easier mode ball moves slower, and it is vise-versa on the harder mode.\n" +
                        "Click START button to start the game, it will open new game window\n" +
                        "Once you are on the game, click enter button on your keyboard to fire the ball\n" +
                        "Move the paddle with left and right arrow keys to move it to the left and right\n" +
                        "Break the bricks and never loose the ball by not catching it with paddle\n" +
                        "Click RESTART GAME button to start a new game\n" +
                        "Click SEE SCORES button to see your score\n" +
                        "Click END GAME button to end the game");
        instructionsTextArea.setOpaque(false); // when painting make not transparent
        instructionsTextArea.setFont(new Font("Tahoma", Font.ITALIC, 16));
        instructionsTextArea.setBounds(72, 89, 664, 255);
        add(instructionsTextArea);

        // Select level label
        selectLevelLabel = new JLabel("PLEASE SELECT GAME MODE");
        selectLevelLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        selectLevelLabel.setBounds(282, 360, 309, 42);
        add(selectLevelLabel);

        // level buttons, toggle
        easyToggleButton = new JToggleButton("Easy");
        gameModeButtonGroup.add(easyToggleButton);
        easyToggleButton.setBounds(72, 524, 121, 37);
        add(easyToggleButton);

        mediumToggleButton = new JToggleButton("Medium");
        gameModeButtonGroup.add(mediumToggleButton);
        mediumToggleButton.setBounds(321, 524, 121, 37);
        add(mediumToggleButton);

        hardToggleButton = new JToggleButton("Hard");
        gameModeButtonGroup.add(hardToggleButton);
        hardToggleButton.setBounds(588, 524, 121, 37);
        add(hardToggleButton);

        //gameLevel.setGAME_LEVEL(getSelectedMode());

        // start button
        startButton = new JButton("START");
        startButton.setBounds(342, 695, 89, 37);
        add(startButton);
    }

    // Getting start button
    public JButton getStartButton() {
        return startButton;
    }

    // Getting selected game mode
    public GameMode getSelectedMode() {
        if (easyToggleButton.isSelected()) {
            return GameMode.EASY;
        }
        if (mediumToggleButton.isSelected()) {
            return GameMode.MEDIUM;
        }
        if (hardToggleButton.isSelected()) {
            return GameMode.HARD;
        }
        return null;
    }

}
