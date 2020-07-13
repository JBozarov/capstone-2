package com.brickbreaker.main;

import com.brickbreaker.game.GameScreenPanel;
import com.brickbreaker.start.GameMode;
import com.brickbreaker.start.StartScreenPanel;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainWindow extends JFrame {

    private JFrame gameWindowFrame;

    private CardLayout cardLayout;
    private StartScreenPanel startScreen;
    private GameScreenPanel gameScreen;

    // constructor creating 2 panels inside a one window
    private MainWindow () {

        // initializing layout
        cardLayout = new CardLayout(0, 0);
        getContentPane().setLayout(cardLayout);

        // initializing the Start screen
        startScreen = new StartScreenPanel();
        startScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Panel start btn clicked ");
                GameMode selectedMode = startScreen.getSelectedMode();
                if (selectedMode == null ) {
                    showMessageDialog(null, "Please select game mode");
                    System.out.println("Please select the game mode");
                }
                else {
                    if (selectedMode == GameMode.EASY){
                        gameScreen.setSpeed(5);
                    }
                    else if(selectedMode == GameMode.MEDIUM){
                        gameScreen.setSpeed(7);
                    }
                    else if(selectedMode == GameMode.HARD){
                        gameScreen.setSpeed(9);
                    }
                    System.out.println("Selected game mode " + selectedMode);
                    showGameScreen();
                }
            }
        });
        getContentPane().add(startScreen, "Start Screen");


        // initializing the Game Screen
        gameScreen = new GameScreenPanel(this, 5, 5);

        // Game Panel restart button
        gameScreen.getButtonPanel().getRestartGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameScreen.resetGame();
            }
        });
        // Game panel and Button panel
        gameScreen.getButtonPanel().getEndGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameScreen.resetGame();
                showStartScreen();
            }
        });
        getContentPane().add(gameScreen, "Game Screen");

        // showing start screen when run
        showStartScreen();
    }

    // show Start Screen
    private void showStartScreen() {
        getContentPane().add(startScreen, "Start Screen");
        cardLayout.show(getContentPane(), "Start Screen");
        repaint();
    }

    // show Game Screen
    private void showGameScreen() {
        getContentPane().add(gameScreen, "Game Screen");
        cardLayout.show(getContentPane(), "Game Screen");
        repaint();
    }

    // Singleton Design Pattern
    private static MainWindow mainWindow = new MainWindow();
    public static MainWindow getMainWindow() {
        return mainWindow;
    }

}
