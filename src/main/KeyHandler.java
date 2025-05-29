package main;// In main/KeyHandler.java

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used for continuous movement, can be left empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Returns the integer keyCode associated with the key in this event

        // Title state
        if(gp.gameState == gp.titleState){
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) { // This will now work as gp is not null
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

    }
}