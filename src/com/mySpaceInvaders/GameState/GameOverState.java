package com.mySpaceInvaders.GameState;

import com.mySpaceInvaders.Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;


public class GameOverState extends GameState {

    // game over state options
    private String[] options = {
            "Yes",
            "No"
    };

    private int currentOption = 0;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // Draw default background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // Draw a box for style
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, GamePanel.HEIGHT / 2 - 90, GamePanel.WIDTH - 100, 50);


        // Draw Game Over to screen
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", GamePanel.HEIGHT / 2 - 100, 150);

        // Draw the Play Again options
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        g.drawString("Play Again?",GamePanel.HEIGHT / 2 - 100, 200);
        g.drawString(options[0], GamePanel.WIDTH / 2, GamePanel.HEIGHT - 200);
        g.drawString(options[1], GamePanel.WIDTH / 2 + 50 , GamePanel.HEIGHT - 200);


        // Draw a box around "yes" and "no" depending on the current option
        if(currentOption == 0) {
            g.drawRect(GamePanel.WIDTH / 2 - 7, GamePanel.HEIGHT - 220, 40,30);
        }
        if(currentOption == 1) {
            g.drawRect(GamePanel.WIDTH / 2 + 40, GamePanel.HEIGHT - 220, 40,30);
        }



    }

    @Override
    public void keyPressed(int k) {

        if(k == KeyEvent.VK_LEFT && currentOption > 0) {
            currentOption--;
        }

        if(k == KeyEvent.VK_RIGHT && currentOption < options.length - 1) {
            currentOption++;
        }

        if(k == KeyEvent.VK_ENTER) {
            selectOption();
        }

    }

    @Override
    public void keyReleased(int k) {

    }

    public void selectOption() {
        if(currentOption == 0) {
            gsm.setState(GameStateManager.MENU_STATE);
        }
        if(currentOption == 1) {
            System.exit(0);
        }
    }

}
