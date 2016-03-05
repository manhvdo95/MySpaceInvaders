package com.mySpaceInvaders.Entity;

import com.mySpaceInvaders.Main.GamePanel;

public class Alien extends Entity {

    private int alienSpeed = 5;

    public Alien(String imagePath, int x, int y) {
        super(imagePath, x, y);
        setDx(alienSpeed);

    }

    public void update() {
        x += dx;

        // Collision with left side of screen
        // move down and change direction
        if ((x < 0)) {
            y += getImage().getHeight();
            setDx(-dx);
        }
        // Collision with right side of screen
        // move down and change direction
        if (x > GamePanel.WIDTH -  getImage().getWidth()) {
            x = GamePanel.WIDTH - getImage().getWidth();
            y += getImage().getHeight();
            setDx(-dx);
        }


    }

    public int getAlienSpeed() {
        return alienSpeed;
    }

    public void setAlienSpeed(int alienSpeed) {
        this.alienSpeed = alienSpeed;
    }


}
