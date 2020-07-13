package com.brickbreaker.game;

import java.awt.*;

public abstract class Block extends Rectangle implements IShape {

    private boolean destroyed;

    public abstract boolean isDestroyed();

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
