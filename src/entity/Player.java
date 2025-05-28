package entity;

import java.awt.*;
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
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 8;
        solidArea.width = 24;
        solidArea.height = 16;

        setDefaultValues();
        getPlayerImage(); // Ensure images are loaded during initialization
    }

    @Override
    public void setDefaultValues(){
        worldX = gp.tileSize * 2;
        worldY = gp.tileSize * 12;
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
                // keyProcessed = true;
                lastMoveTime = currentTime;
            } else if (keyH.downPressed) {
                direction = "down";
                // keyProcessed = true;
                lastMoveTime = currentTime;
            } else if (keyH.leftPressed) {
                direction = "left";
                // keyProcessed = true;
                lastMoveTime = currentTime;
            } else if (keyH.rightPressed) {
                direction = "right";
                // keyProcessed = true;
                lastMoveTime = currentTime; 
            }
        
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if(collisionOn == false){

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    default:
                }
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
        if (worldX < 0 || worldX >= gp.maxScreenCol * gp.tileSize || worldY < 0 || worldY >= gp.maxScreenRow * gp.tileSize) {
            handleMapChange();
        }

        // Reset keyProcessed when no keys are pressed
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            keyProcessed = false;
        }
    }

    private void handleMapChange() {
        if (worldX < 0) {
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
            worldX = (gp.maxScreenCol - 1) * gp.tileSize; // Adjust player position to the opposite side
        } else if (worldX >= gp.maxScreenCol * gp.tileSize) {
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
            worldX = 0; // Adjust player position to the opposite side
        } else if (worldY < 0) {
            // Handle vertical transitions (if applicable)
            // Example: Add logic for moving up between maps
        } else if (worldY >= gp.maxScreenRow * gp.tileSize) {
            // Handle vertical transitions (if applicable)
            // Example: Add logic for moving down between maps
        }
    }

    public void resetPosition() {
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 12;
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
            g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null); // Draw the player image
        } else {
            System.out.println(image);
            g2.setColor(java.awt.Color.white);
            g2.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);
        }
    }

}