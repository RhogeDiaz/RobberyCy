package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX, worldY;
    protected int speed;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected String direction; // Direction of the entity (up, down, left, right)
    protected int spriteCounter = 0; // Counter for sprite animation
    protected int spriteNum = 1; // Current sprite number (1 or 2)
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    // Getters and setters
    public int getX() { return worldX; }
    public void setX(int x) { this.worldX = x; }

    public int getY() { return worldY; }
    public void setY(int y) { this.worldY = y; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }

    public int getSpriteCounter() { return spriteCounter; }
    public void setSpriteCounter(int spriteCounter) { this.spriteCounter = spriteCounter; }

    public int getSpriteNum() { return spriteNum; }
    public void setSpriteNum(int spriteNum) { this.spriteNum = spriteNum; }

    public BufferedImage getUp1() { return up1; }
    public BufferedImage getUp2() { return up2; }
    public BufferedImage getDown1() { return down1; }
    public BufferedImage getDown2() { return down2; }
    public BufferedImage getLeft1() { return left1; }
    public BufferedImage getLeft2() { return left2; }
    public BufferedImage getRight1() { return right1; }
    public BufferedImage getRight2() { return right2; }

    // Abstract methods
    public abstract void setDefaultValues();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g2);
}
