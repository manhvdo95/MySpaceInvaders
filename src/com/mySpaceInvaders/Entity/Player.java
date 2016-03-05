package com.mySpaceInvaders.Entity;

import com.mySpaceInvaders.Main.GamePanel;

public class Player extends Entity {

    private int playerSpeed = 5;

    public Player(String imagePath, int x, int y) {
        super(imagePath, x, y);
        setDx(playerSpeed);

    }

    public void update() {
        x += dx;

        // Collision with left side of screen
        if ((x < 0)) {
            x = 0;
        }
        // Collision with right side of screen
        if (x > GamePanel.WIDTH -  getImage().getWidth()) {
            x = GamePanel.WIDTH - getImage().getWidth();
        }

    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }


}
