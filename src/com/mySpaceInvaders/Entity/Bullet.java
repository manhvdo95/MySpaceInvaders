package com.mySpaceInvaders.Entity;

public class Bullet extends Entity{

    private int bulletSpeed = -10;

    public Bullet(String imagePath, int x, int y) {
        super(imagePath, x, y);
        dy = bulletSpeed;
    }

    public void update() {
        y += dy;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }


}
