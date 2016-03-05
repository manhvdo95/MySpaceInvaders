package com.mySpaceInvaders.GameState;

import com.mySpaceInvaders.Entity.*;
import com.mySpaceInvaders.Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayState extends GameState {

    // image string path
    private final String playerPath = "images/player.png";
    private final String alienPath = "images/alien.png";
    private final String bulletPath = "images/bullet.png";

    // starting coordinates
    private final int PLAYER_START_X = GamePanel.WIDTH / 2;
    private final int PLAYER_START_Y = GamePanel.HEIGHT - 100;
    private int alien_start_x = 5;
    private int alien_start_y = 150;

    // player movement
    private boolean moveLeft;
    private boolean moveRight;

    // entities
    private Player player;
    private ArrayList entities = new ArrayList();
    private ArrayList removeList = new ArrayList();

    // Used for win/lose condition
    private int alienCount;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    public void gameOver() {
        gsm.setState(GameStateManager.GAMEOVER_STATE);
    }

    // Create a bullet when player is firing
    public void fire() {
        Bullet bullet = new Bullet(bulletPath,
                player.getX() + player.getImage().getWidth() / 2
                ,player.getY() - 1
        );
        entities.add(bullet);

    }

    @Override
    public void init() {
        entities.clear();
        moveLeft = false;
        moveRight = false;
        alienCount = 0;

        player = new Player(playerPath, PLAYER_START_X, PLAYER_START_Y);
        entities.add(player);

        // create aliens 5 row 8 col
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 8; col++) {
                Alien alien = new Alien(alienPath, alien_start_y + col * 25, alien_start_x + row * 25);
                entities.add(alien);
                alienCount++;
            }
        }

    }

    @Override
    public void update() {

        // Update player movement
        if (moveLeft) {
            player.setDx(-player.getPlayerSpeed());
        }
        else if (moveRight) {
            player.setDx(player.getPlayerSpeed());
        }
        else {
            player.setDx(0);
        }

        // Update entities (player, aliens and bullet)
        for(int i = 0 ; i < entities.size(); i++) {
            Entity entity =  (Entity) entities.get(i);
            entity.update();

        }

        // Collision with ALL entities
        for(int i = 0; i < entities.size(); i++) {
            for(int j = i + 1; j < entities.size(); j++) {
                Entity thisEntity = (Entity) entities.get(i);
                Entity otherEntity = (Entity) entities.get(j);

                if(thisEntity.collisionWith(otherEntity)) {
                    // if there is collision with player, then game over
                    if(thisEntity instanceof Player) {
                        gameOver();
                    }

                    // add to removelist if there was a collsion
                    removeList.add(thisEntity);
                    removeList.add(otherEntity);

                    alienCount--;
                }

            }
        }

        // win condition
        if(alienCount == 0) {
            gameOver();
        }

        //remove entities that are from the removelist
        entities.removeAll(removeList);

        //clear everything in removelist
        removeList.clear();

    }

    @Override
    public void draw(Graphics2D g) {

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // Draw player
        player.draw(g);

        // Draw aliens and bullets
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = (Entity) entities.get(i);

            entity.draw(g);
        }

    }

    @Override
    public void keyPressed(int k) {

        if(k == KeyEvent.VK_LEFT) {
            moveLeft = true;
        }

        if(k == KeyEvent.VK_RIGHT) {
            moveRight = true;
        }

        if(k == KeyEvent.VK_SPACE) {
            fire();
        }

        if(k == KeyEvent.VK_Q) {
            gameOver();
        }

    }

    @Override
    public void keyReleased(int k) {

        if (k == KeyEvent.VK_LEFT) {
            moveLeft = false;
        }

        if (k == KeyEvent.VK_RIGHT) {
            moveRight = false;
        }

        if(k == KeyEvent.VK_SPACE) {

        }
    }


}
