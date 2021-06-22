package com.brickbreaker.game;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameScreenPanel extends JPanel {

    private GamePanel gamePanel;
    private ButtonsPanel buttonsPanel;

    // constructor
    public GameScreenPanel(JFrame mainFrame, int ballMoveX, int ballMoveY) {
        setLayout(new BorderLayout(0, 0));

        gamePanel = new GamePanel(mainFrame, ballMoveX, ballMoveY);
        add(gamePanel);

        buttonsPanel = new ButtonsPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setSpeed(int newSpeed) {
        gamePanel.setSpeed(newSpeed);
    }

    public void resetGame() {
        gamePanel.resetGame();
    }

    public ButtonsPanel getButtonPanel () {
        return buttonsPanel;
    }

}
