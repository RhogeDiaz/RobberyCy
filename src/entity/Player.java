package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.SuperObject;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;
    private boolean keyProcessed = false;
    private long lastMoveTime = 0; // Track the last movement time
    private final int moveDelay = 0; // Delay in milliseconds
    public String currentMap = "map1"; // Track the current map

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 16;
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

// In entity/Player.java

    @Override
    public void update() {
        // 1. ALWAYS RESET collisionOn at the start of the frame.
        // This is crucial for correctly handling movement every frame.
        collisionOn = false;

        // 2. Determine player's intended direction based on CURRENT key presses.
        // This 'if-else if' chain ensures 'direction' is set based on priority (e.g., up > down)
        // if multiple keys are pressed, or to the last pressed key.
        if (keyH.upPressed) {
            direction = "up";
        } else if (keyH.downPressed) {
            direction = "down";
        } else if (keyH.leftPressed) {
            direction = "left";
        } else if (keyH.rightPressed) {
            direction = "right";
        }
        // If no movement keys are pressed, 'direction' will retain its last value.
        // This is fine for animation purposes, but the movement itself will be gated below.


        // 3. Perform all collision checks. These should run every frame.
        gp.cChecker.checkTile(this); // Check for tile collisions
        int objIndex = gp.cChecker.checkObject(this, true); // Check for object collisions/interactions
        pickUpObject(objIndex); // Process object interaction


        // 4. Move the player ONLY IF movement keys are actively pressed AND no collision was detected.
        // This 'if' statement is CRUCIAL to stop movement when keys are released.
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (collisionOn == false) { // Only move if no collision was detected
                switch (direction) { // 'direction' will be the one set in step 2
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
                    // No 'default' case needed here, as 'direction' will always be one of the above if a key is pressed.
                }
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) { // Adjust the speed of sprite animation
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0; // Reset the counter
        }

        // 6. Check for map boundaries/changes (runs every frame)
        if (worldX < 0 || worldX >= gp.maxScreenCol * gp.tileSize || worldY < 0 || worldY >= gp.maxScreenRow * gp.tileSize) {
            handleMapChange();
        }

        // --- OBSTACLE COLLISION LOGIC ---
        if (gp.cChecker.checkObstacleCollision(this)) {
            gp.gameThread = null; // Pause the game loop
            javax.swing.SwingUtilities.invokeLater(() -> {
                int result = javax.swing.JOptionPane.showOptionDialog(
                    null,
                    "You've been caught!",
                    "Game Over",
                    javax.swing.JOptionPane.DEFAULT_OPTION,
                    javax.swing.JOptionPane.ERROR_MESSAGE,
                    null,
                    new Object[]{"Retry", "Quit"},
                    "Retry"
                );
                if (result == 0) { // Retry
                    setDefaultValues();
                    gp.currentMapCollectedChests = 0;
                    gp.player.hasKey = 0;
                    // Reset all obstacles to their default positions
                    if (gp.currentMapObstacles != null) {
                        for (entity.Obstacle obs : gp.currentMapObstacles) {
                            obs.setDefaultValues();
                        }
                    }
                    // Reset all objects (keys, chests, etc.) to their original positions
                    gp.aSetter.setObjectsForAllMaps();
                    gp.currentMapObjects = gp.mapObjects.get(currentMap);
                    // --- Clear movement keys to prevent auto-move bug ---
                    gp.keyH.upPressed = false;
                    gp.keyH.downPressed = false;
                    gp.keyH.leftPressed = false;
                    gp.keyH.rightPressed = false;
                    gp.elapsedMillis = 0;
                    gp.lastUpdateTime = System.nanoTime();
                    gp.gameThread = new Thread(gp);
                    gp.gameThread.start();
                } else { // Quit
                    System.exit(0);
                }
            });
        }

    }

    public void pickUpObject(int i) {

        if (i != 999) {
            SuperObject obj = gp.currentMapObjects.get(i); // Get the object instance

            String objectName = obj.name; // Get the name for specific handling

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.currentMapObjects.remove(i);
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door Blue":
                    if(hasKey > 0){
                        gp.currentMapObjects.remove(i);
                        hasKey--;
                        gp.ui.showMessage("You opened a blue door!");
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Chest":
                    gp.currentMapCollectedChests++;
                    gp.currentMapObjects.remove(i);

                    System.out.println("Chests collected: " + gp.currentMapCollectedChests +
                            " / " + gp.totalChestsOnCurrentMap);

                    if (gp.currentMapCollectedChests >= gp.totalChestsOnCurrentMap) {
                        gp.ui.showMessage("Proceed to next level!");
                    }
                    break;
                case "Door":
                    if (gp.currentMapCollectedChests >= gp.totalChestsOnCurrentMap) {
                        gp.currentMapObjects.remove(i);
                        gp.ui.showMessage("To the next level!");

                    }
                    else {
                        gp.ui.showMessage("Collect all the treasure first!");
                    }
                    break;
                case "FinalDoor":
                    gp.ui.gameFinished =  true;
                    break;
            }

        }
    }
// In Player.java

    private void handleMapChange() {
        String nextMapName = currentMap; // Initialize with current map

        int playerCurrentWorldX = worldX;
        int playerCurrentWorldY = worldY;

        // --- Horizontal Transitions ---
        if (playerCurrentWorldX < 0) { // Exiting left
            if (currentMap.equals("map1")) { nextMapName = "map3"; }
            else if (currentMap.equals("map3")) { nextMapName = "map2"; }
            else if (currentMap.equals("map2")) { nextMapName = "map1"; }

            worldX = (gp.maxScreenCol - 1) * gp.tileSize;

        } else if (playerCurrentWorldX >= gp.maxScreenCol * gp.tileSize) { // Exiting right
            if (currentMap.equals("map1")) { nextMapName = "map2"; }
            else if (currentMap.equals("map2")) { nextMapName = "map3"; }
            else if (currentMap.equals("map3")) { nextMapName = "map1"; }

            worldX = 0;
        }

        if (!nextMapName.equals(currentMap)) {
            gp.tileM.changeMap(nextMapName);
            currentMap = nextMapName;

            gp.currentMapObjects = gp.mapObjects.get(nextMapName);
            gp.currentMapObstacles = gp.mapObstacles.get(nextMapName);
        }
        gp.currentMapCollectedChests = 0;
        gp.totalChestsOnCurrentMap = gp.mapTotalChestCounts.get(currentMap);
    }

    public void resetPosition() {
        worldX = gp.tileSize * 2;
        worldY = gp.tileSize * 12;
        speed = gp.tileSize / 8;
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