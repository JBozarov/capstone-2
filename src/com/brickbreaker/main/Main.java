package com.brickbreaker.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MainWindow mainWindow = MainWindow.getMainWindow();

        mainWindow.setSize(800, 1000);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        mainWindow.initiateUI();
    }
}
