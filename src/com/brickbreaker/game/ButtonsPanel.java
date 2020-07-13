package com.brickbreaker.game;

import javax.swing.JPanel;
import javax.swing.JButton;

public class ButtonsPanel extends JPanel {
    private JButton restartGameButton;
    private JButton scoreButton;
    private JButton endGameButton;

    // constructor
    public ButtonsPanel() {
        restartGameButton = new JButton("Restart Game");
        add(restartGameButton);

        scoreButton = new JButton("See Score");
        add(scoreButton);

        endGameButton = new JButton("End Game");
        add(endGameButton);
    }

    public JButton getRestartGameButton() {
        return restartGameButton;
    }

    public JButton getScoreButton() {
        return scoreButton;
    }

    public JButton getEndGameButton() {
        return endGameButton;
    }
}
