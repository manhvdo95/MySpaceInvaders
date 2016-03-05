package com.mySpaceInvaders.Main;

import com.mySpaceInvaders.GameState.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.image.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private Thread thread;

    private BufferedImage image;
    private Graphics2D g;
    private boolean running;

    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            addKeyListener(this);
            thread = new Thread(this);
            thread.start();
        }
    }

    // initializes variables
    private void init() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();

    }

    // the "main" function
    public void run() {

        init();

        int FPS = 30;
        int targetTime = 1000 / FPS;

        long start;
        long elapsed;
        long wait;

        // simple game loop
        while(running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = (System.nanoTime() - start) / 1000000;

            wait = targetTime - elapsed;
            if(wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    // updates the game
    private void update() {
        gsm.update();
    }

    // draws the game onto an off-screen buffered image
    private void draw() {
        gsm.draw(g);
    }

    // draws the off-screen buffered image to the screen
    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    // key input
    public void keyTyped(KeyEvent k) {

    }

    public void keyPressed(KeyEvent k) {
        gsm.keyPressed(k.getKeyCode());;
    }

    public void keyReleased(KeyEvent k) {
        gsm.keyReleased(k.getKeyCode());
    }


}