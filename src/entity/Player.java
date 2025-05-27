package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    private boolean keyProcessed = false;
    private long lastMoveTime = 0; // Track the last movement time
    private final int moveDelay = 0; // Delay in milliseconds
    private String currentMap = "map1"; // Track the current map

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage(); // Ensure images are loaded during initialization
    }

    @Override
    public void setDefaultValues(){
        x = gp.tileSize * 15;
        y = gp.tileSize * 12;
        speed = gp.tileSize / 8;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Log the error to debug image loading issues
        }
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis(); // Get the current time

        if (!keyProcessed && (currentTime - lastMoveTime >= moveDelay)) {

            if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
                // keyProcessed = true;
                lastMoveTime = currentTime;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed; 
                // keyProcessed = true;
                lastMoveTime = currentTime;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
                // keyProcessed = true;
                lastMoveTime = currentTime;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
                // keyProcessed = true;
                lastMoveTime = currentTime; 
            }
        

        // Update sprite animation
        spriteCounter++;
        if (spriteCounter > 12) { // Adjust the speed of sprite animation
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0; // Reset the counter
        }
    }
    }

        // Check if the player has moved outside the map boundaries
        if (x < 0 || x >= gp.maxScreenCol * gp.tileSize || y < 0 || y >= gp.maxScreenRow * gp.tileSize) {
            handleMapChange();
        }

        // Reset keyProcessed when no keys are pressed
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            keyProcessed = false;
        }
    }

    private void handleMapChange() {
        if (x < 0) {
            if (currentMap.equals("map1")) {
                gp.tileM.changeMap("map3"); // Move to map3 when exiting left from map1
                currentMap = "map3";
            } else if (currentMap.equals("map3")) {
                gp.tileM.changeMap("map2"); // Move to map2 when exiting left from map3
                currentMap = "map2";
            } else if (currentMap.equals("map2")) {
                gp.tileM.changeMap("map1"); // Move to map1 when exiting left from map2
                currentMap = "map1";
            }
            x = (gp.maxScreenCol - 1) * gp.tileSize; // Adjust player position to the opposite side
        } else if (x >= gp.maxScreenCol * gp.tileSize) {
            if (currentMap.equals("map1")) {
                gp.tileM.changeMap("map2"); // Move to map2 when exiting right from map1
                currentMap = "map2";
            } else if (currentMap.equals("map2")) {
                gp.tileM.changeMap("map3"); // Move to map3 when exiting right from map2
                currentMap = "map3";
            } else if (currentMap.equals("map3")) {
                gp.tileM.changeMap("map1"); // Move to map1 when exiting right from map3
                currentMap = "map1";
            }
            x = 0; // Adjust player position to the opposite side
        } else if (y < 0) {
            // Handle vertical transitions (if applicable)
            // Example: Add logic for moving up between maps
        } else if (y >= gp.maxScreenRow * gp.tileSize) {
            // Handle vertical transitions (if applicable)
            // Example: Add logic for moving down between maps
        }
    }

    public void resetPosition() {
        x = gp.tileSize * 15;
        y = gp.tileSize * 12;
        direction = "right";
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        if (image != null) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // Draw the player image
        } else {
            System.out.println(image);
            g2.setColor(java.awt.Color.white);
            g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        }
    }

}