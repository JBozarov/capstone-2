package com.brickbreaker.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static javax.swing.JOptionPane.showMessageDialog;

public class GamePanel extends JPanel implements KeyListener {

    private ArrayList<GameBlock> blocks;
    private GameBlock welcome;
    private GameBlock wall;
    private GameBlock ball;
    private GameBlock paddle;

    private Thread thread;
    private boolean gameStarted;
    private boolean pressed;

    private int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
    private String[] colors = { "redBlock.png", "greyBlock.png", "whiteBlock.png", "purpleBlock.png", "orangeBlock.png",
            "blueBlock.png", "blue2.png", "pink.png" };

    private JFrame mainFrame;

    private int initialBallMoveX;
    private int initialBallMoveY;

    private int ballMoveX;
    private int ballMoveY;

    private int pressedKey;
    private int count;


    // constructor
    public GamePanel(JFrame frame, int ballMoveX, int ballMoveY) {
        this.mainFrame = frame;
        this.initialBallMoveX = ballMoveX;
        this.initialBallMoveY = ballMoveY;

        initializeGame();
    }

    public void resetGame() {
        initializeGame();
    }

    // initialize the game
    public void initializeGame() {
        gameStarted = false;

        ballMoveX = initialBallMoveX;
        ballMoveY = initialBallMoveY;

        // array list that will contain bricks of the wall
        blocks = new ArrayList<GameBlock>();

        welcome = new GameBlock(100, 0, 528, 65, "welcome.png"); // the title
        paddle = new GameBlock(315, 780, 200, 30, "greenBackPaddle.png"); // the paddle
        ball = new GameBlock(400, 747, 35, 35, "ball.png"); // the ball itself

        // initialize brick will by creating 4 rows of bricks at different Y position
        createWall(70);
        createWall(100);
        createWall(130);
        createWall(160);
        System.out.println(blocks.size());

        //setOpaque(false); //
        setBackground(new Color(163, 210, 119));
        setPreferredSize(new Dimension(800, 1000));

        // initialize keyboard key listener. This class will be listening to keyboard events
        addKeyListener(this);

        // in order to make sure that this component listens to key events, I need to make it focusable
        setFocusable(true);
        requestFocus();
        grabFocus();
        requestFocusInWindow(true);

        repaint();
    }

    // creating a wall, using stream
    public void createWall(int y) {
        Arrays.stream(numbers).forEach(x -> {
            blocks.add(new GameBlock((x * 67), y, 64, 25, colors[(int) Math.floor(Math.random() * 8)]));
        });
    }

    // creates the images
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        welcome.createImage(g, this);
        blocks.forEach(brick -> {
            brick.createImage(g, this);
        });
        ball.createImage(g, this);
        paddle.createImage(g, this);
        // System.out.println("Painting");
    }

    // method checks blocks size
    public boolean checkSize() {
        // using stream
        ArrayList<GameBlock> copy = (ArrayList<GameBlock>) blocks
                .stream()
                .filter((brick -> brick.isDestroyed() == true))
                .collect(Collectors.toList());
        if (copy.size()==blocks.size()) {
            return true;
        }
        return false;
    }

    // update the game panel
    public void update() {

        // moving the ball X axis
        ball.x += ballMoveX;
        if (ball.x > getWidth() - 35 || ball.x < 0 ) {
            ballMoveX *= -1;
        }

        // moving the ball Y axis
        ball.y -= ballMoveY;
        if (ball.y < 70 || ball.intersects(paddle)) {
            ballMoveY *= -1;
        }

        // if we lose the ball
        if (ball.y > getHeight()) {
            showMessageDialog(null, "You lost the ball!");

            // we stop the thread by setting gameStarted to false
            gameStarted = false;
            resetGame();
        }

        if (checkSize()) {
            showMessageDialog(null, "You won the game!");
            gameStarted = false;
            resetGame();
        }


        // using stream to iterate through collection of bricks and determine if brick is destroyed by the ball
        blocks.stream().forEach(brick -> {
            if (ball.intersects(brick)) {
                if (brick.isDestroyed()) {
                    ballMoveY *= -1;
                }
                ballMoveY *= -1;
                brick.setDestroyed(true);
                if (!brick.isDestroyed()) {
                    System.out.println("Count is " + count);
                    count += 1;
                }

            }
        });
        repaint();
    }

    // Setting  game mode
    public void setSpeed(int newSpeed){
        ballMoveX = newSpeed;
        ballMoveY = newSpeed;

        initialBallMoveX = newSpeed;
        initialBallMoveY = newSpeed;
    }

    // using lambda
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKey = e.getKeyCode();

        if(e.getKeyCode() == KeyEvent.VK_ENTER && !gameStarted ) {
            System.out.println("Enter pressed");
            gameStarted = true;
            thread = new Thread(() -> {
                while (gameStarted) {
                    update();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        // moving paddle
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < getWidth() - paddle.width - 15 && !pressed) {
            pressed = true;
            paddle.x += 35;

        }

        // moving left
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 20 && !pressed) {
            pressed = true;
            paddle.x -= 35;
        }

        // Validation on wrong key
        if(e.getKeyCode() != KeyEvent.VK_LEFT &&
                e.getKeyCode() != KeyEvent.VK_RIGHT &&
                e.getKeyCode() != KeyEvent.VK_ENTER && !pressed){
                pressed = true;
                showMessageDialog(null, "An invalid key was typed: "+Character.toString((char)e.getKeyCode())+" Please press ENTER to start the game ");
                System.out.println("An invalid key was typed: "+Character.toString((char)e.getKeyCode()));
        }
    }

    // prevents multiple key press fire
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && pressed || e.getKeyCode() == KeyEvent.VK_LEFT && pressed || e.getKeyCode() == pressedKey) {
            pressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
