package com.brickbreaker.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

public class GameBlock extends Block  {

    private Image image;
    private boolean destroyed;

    // constructor
    public GameBlock(int x, int y, int w, int h, String imageFileName) {
        this.x = x; // current x position
        this.y = y; // current y position
        this.width = w; // the width of the block
        this.height = h; // the height of the block

        try {
            image = ImageIO.read(new File("gameAssets/" + imageFileName));
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    // getting destroyed
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    // setting destroyed
    @Override
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public void createImage(Graphics graphics, Component component) {
        if (!destroyed) {
            graphics.drawImage(image, x, y, width, height, component);
        }
    }
}
