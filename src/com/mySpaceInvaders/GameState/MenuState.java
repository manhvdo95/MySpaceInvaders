package com.mySpaceInvaders.GameState;

import com.mySpaceInvaders.Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by manhdo on 2/25/16.
 */

public class MenuState extends GameState{

    // image string path
    private String backgroundPath = "images/space_invader_background.jpg";
    private String playerIconPath = "images/player.png";

    // images
    private BufferedImage backgroundImage;
    private BufferedImage playerImage;

    // menu options that will be displayed
    private int currentOption = 0;
    private String[] options = {
            "Start",
            "Quit"
    };

    public MenuState(GameStateManager gsm) {
        super(gsm);
        init();

    }


    @Override
    public void init() {

        try {
            backgroundImage = ImageIO.read(new FileInputStream(backgroundPath));
            playerImage = ImageIO.read(new FileInputStream(playerIconPath));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // Default background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // Draw background
        g.drawImage(
                backgroundImage,
                100,
                100,
                null);

        // Draw title
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.setColor(Color.GREEN);
        g.drawString("Space Invaders", GamePanel.HEIGHT / 2 - 150, 100);

        // Draw menu options
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        g.setColor(Color.WHITE);
        g.drawString(options[0], GamePanel.WIDTH / 2 - 20 , GamePanel.HEIGHT - 100);
        g.drawString(options[1], GamePanel.WIDTH / 2 - 20 , GamePanel.HEIGHT - 75);

        // Drawing a player icon next to "yes" or "no" depending on the current option
        if(currentOption == 0) {
            g.drawImage(
                    playerImage,
                    GamePanel.WIDTH / 2 - 60,
                    GamePanel.HEIGHT / 2 + 90,
                    null);
        }
        else if(currentOption == 1) {
            g.drawImage(
                    playerImage,
                    GamePanel.WIDTH / 2 - 60,
                    GamePanel.HEIGHT / 2 + 115,
                    null);

        }

    }

    @Override
    public void keyPressed(int k) {

        if(k == KeyEvent.VK_UP && currentOption > 0) {
            currentOption--;
        }

        if(k == KeyEvent.VK_DOWN && currentOption < options.length - 1) {
            currentOption++;
        }

        if(k == KeyEvent.VK_ENTER) {
            selectOption();
        }

    }

    @Override
    public void keyReleased(int key) {

    }

    public void selectOption() {
        if(currentOption == 0) {
            gsm.setState(GameStateManager.PLAY_STATE);
        }
        if(currentOption == 1) {
            System.exit(0);
        }
    }


}
