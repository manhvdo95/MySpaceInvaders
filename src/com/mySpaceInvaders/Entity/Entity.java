package com.mySpaceInvaders.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class Entity {

    // image
    private BufferedImage image;

    // coordinates
    protected int x;
    protected int y;

    // change in position
    protected int dx;
    protected int dy;

    // Used for collision
    protected Rectangle rectangle1 = new Rectangle();
    protected Rectangle rectangle2 = new Rectangle();

    public Entity(String imagePath, int x, int y) {
        setImage(imagePath);
        this.x = x;
        this.y = y;
    }

    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new FileInputStream(imagePath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDx() {
        return dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDy() {
        return dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y,null);
    }

    public void update() {

    }

    public boolean collisionWith(Entity other) {
        rectangle1.setBounds((int)x, (int) y, image.getWidth(), image.getHeight());
        rectangle2.setBounds((int) other.x, (int) other.y, other.image.getWidth(), other.image.getHeight());

        return rectangle1.intersects(rectangle2);

    }



}
