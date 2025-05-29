package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Daydream", Font.PLAIN, 40);
        arial_80B = new Font("Daydream", Font.BOLD, 50);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        //Title State
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState) {
            drawPlayScreen();
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawTitleScreen() {

        //Background
        g2.setColor(new Color(214, 92, 6, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70));
        String text = "Buggy Robber";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 5;

        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 10, y + 10);

        // Main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Character Image
        x = gp.screenWidth / 2 - gp.tileSize * 2;
        y += gp.tileSize * 4;
        g2.drawImage(gp.player.getDown1(), x, y, gp.tileSize * 4, gp.tileSize * 4, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

        text = "PLAY GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 7;
        g2.setColor(Color.black);
        g2.drawString(text, x + 10, y + 10);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }


        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 10, y + 10);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100));
        String text  = "Paused";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawPlayScreen(){
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
            g2.setColor(Color.black);
            g2.drawString(text, x + 10, y + 10);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.setColor(Color.black);
            g2.drawString(text, x + 10, y + 10);
            g2.setColor(Color.green);
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
            // --- TIMER CODE END ---

            if (messageOn) {

                g2.setFont(g2.getFont().deriveFont(32F));
                g2.drawString(message, gp.tileSize, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public int getXforCenteredText(String text){
        int length  = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
