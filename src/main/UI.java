package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if(gameFinished){

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You finished all the levels!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize, gp.tileSize * 1, gp.tileSize * 2, gp.tileSize * 2, null);
            g2.drawString("x " + gp.player.hasKey, 95, 80);

            // --- TIMER DRAW ---
            long totalMillis = gp.elapsedMillis;
            long minutes = (totalMillis / 1000) / 60;
            long seconds = (totalMillis / 1000) % 60;
            long millis = totalMillis % 1000;
            String timerText = String.format("Time: %02d:%02d.%03d", minutes, seconds, millis);
            int timerTextWidth = g2.getFontMetrics().stringWidth(timerText);
            g2.drawString(timerText, gp.screenWidth - timerTextWidth - gp.tileSize, gp.tileSize * 2 + 20);

            if (messageOn) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize, gp.tileSize * 4);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
